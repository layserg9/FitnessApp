package com.vlados.retrofitapp.data.local

import android.util.Log
import com.vlados.retrofitapp.data.ExerciseDataBase
import com.vlados.retrofitapp.data.ExerciseEntity
import com.vlados.retrofitapp.data.ImagesTypeConverter
import com.vlados.retrofitapp.data.Weekdays
import com.vlados.retrofitapp.data.remote.retrofit.Exercise
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrainingPlanLocalDataSource @Inject constructor(
    private val exerciseDataBase: ExerciseDataBase
) {
    private val trainingPlanMapProcessor = BehaviorProcessor.create<Map<Weekdays, Set<Exercise>>>()
    private val exerciseDao = exerciseDataBase.getExerciseDao()
    private val imagesTypeConverter = ImagesTypeConverter()

    init {
        Completable.fromAction {
            val exerciseEntitiesFromDataBase: List<ExerciseEntity> = exerciseDao.getAllItems()

            Log.d("MyDataBase", "DAO getAllItems: $exerciseEntitiesFromDataBase")

            val exerciseEntitiesToListPair =
                exerciseEntitiesFromDataBase.groupBy { exerciseEntity -> exerciseEntity.weekDay }
                    .filter { (weekDay) ->
                        weekDay < Weekdays.values().size && weekDay >= 0
                    }
                    .flatMap { (weekDay, listExerciseEntity) ->
                        listExerciseEntity.map { exerciseEntity ->
                            Weekdays.values()[weekDay] to Exercise(
                                id = exerciseEntity.id,
                                name = exerciseEntity.name,
                                description = exerciseEntity.description,
                                images = imagesTypeConverter.toImages(exerciseEntity.images)
                            )
                        }
                    }

            Log.d("MyDataBase", "Entity to listPair: $exerciseEntitiesToListPair")

            val listPairToMap = exerciseEntitiesToListPair
                .groupBy({ it.first }, { it.second })
                .mapValues { (_, exercises) -> exercises.toSet() }
                .toSortedMap()
            trainingPlanMapProcessor.onNext(listPairToMap)

            Log.d("MyDataBase", "Получаем из БД: ${listPairToMap.toString()}")
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun getTrainingPlanMapFlow(): Flowable<Map<Weekdays, Set<Exercise>>> {
        return trainingPlanMapProcessor
    }

    fun addExerciseToTrainingPlanMap(weekDay: Weekdays, exercise: Set<Exercise>) {
        val currentMap = trainingPlanMapProcessor.value?.toMutableMap() ?: mutableMapOf()
        val exerciseSet = currentMap[weekDay]?.toMutableSet() ?: mutableSetOf()
        exerciseSet.addAll(exercise)
        currentMap[weekDay] = exerciseSet
        trainingPlanMapProcessor.onNext(currentMap)
        addExerciseToDataBase(currentMap)

        Log.d("MyDataBase", "Добавляем в PlanMap: $currentMap")
    }

    private fun addExerciseToDataBase(listOfExercises: Map<Weekdays, Set<Exercise>>) {
        Completable.fromAction {
            exerciseDao.insertItems(convertListPairToListEntity(convertMapToList(listOfExercises)))

            Log.d("MyDataBase", "Добавляем в БД: $listOfExercises")
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    private fun convertMapToList(
        map: Map<Weekdays, Set<Exercise>>
    ): List<Pair<Weekdays, Exercise>> {
        return map.flatMap { (weekday, exercises) ->
            exercises.map { exercise -> weekday to exercise }
        }
    }

    private fun convertListPairToListEntity(
        pairList: List<Pair<Weekdays, Exercise>>
    ): List<ExerciseEntity> {
        return pairList.map { (weekday, exercise) ->
            ExerciseEntity(
                id = exercise.id,
                name = exercise.name,
                description = exercise.description,
                images = imagesTypeConverter.fromImages(exercise.images),
                weekDay = weekday.ordinal
            )
        }
    }
}