package com.vlados.retrofitapp.data.local

import com.vlados.retrofitapp.data.ExerciseDao
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
    private val exerciseDao: ExerciseDao
) {
    private val trainingPlanMapProcessor = BehaviorProcessor.create<Map<Weekdays, Set<Exercise>>>()
    private val imagesTypeConverter = ImagesTypeConverter()

    init {
        runOnIoScheduler {
            val exerciseEntitiesFromDataBase: List<ExerciseEntity> = exerciseDao.getAllItems()
            val exercisesMap =
                exerciseEntitiesFromDataBase.groupBy(
                    { exerciseEntity ->
                        val weekDay = exerciseEntity.weekDay
                        if (weekDay < Weekdays.values().size && weekDay >= 0) {
                            Weekdays.values()[weekDay]
                        } else {
                            Weekdays.MONDAY
                        }
                    },
                    { exerciseEntity ->
                        Exercise(
                            id = exerciseEntity.id,
                            name = exerciseEntity.name,
                            description = exerciseEntity.description,
                            images = imagesTypeConverter.toImages(exerciseEntity.images)
                        )
                    }
                )
                    .mapValues { (_, exercises) -> exercises.toSet() }
                    .toSortedMap()
            trainingPlanMapProcessor.onNext(exercisesMap)
        }
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
    }

    fun deleteExerciseFromTrainingPlanMap(weekDay: Weekdays, exercise: Exercise) {
        val currentMap = trainingPlanMapProcessor.value?.toMutableMap() ?: mutableMapOf()
        val exerciseSet = currentMap[weekDay]?.toMutableSet() ?: mutableSetOf()
        exerciseSet.remove(exercise)
        currentMap[weekDay] = exerciseSet
        trainingPlanMapProcessor.onNext(currentMap)
        deleteExerciseFromDataBase(weekDay, exercise)
    }

    private fun addExerciseToDataBase(mapOfExercises: Map<Weekdays, Set<Exercise>>) {
        runOnIoScheduler {
            val listPair = convertMapToList(mapOfExercises)
            val listExerciseEntity = convertListPairToListEntity(listPair)
            exerciseDao.insertItems(listExerciseEntity)
        }
    }

    private fun deleteExerciseFromDataBase(weekDay: Weekdays, exercise: Exercise) {
        runOnIoScheduler {
            val exerciseEntity = getExerciseEntity(weekDay, exercise)
            exerciseDao.deleteItems(exerciseEntity)
        }
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

    private fun runOnIoScheduler(action: () -> Unit) {
        Completable.fromAction(action).subscribeOn(Schedulers.io()).subscribe()
    }

    private fun getExerciseEntity(weekday: Weekdays, exercise: Exercise): ExerciseEntity {
        return ExerciseEntity(
            id = exercise.id,
            name = exercise.name,
            description = exercise.description,
            images = imagesTypeConverter.fromImages(exercise.images),
            weekDay = weekday.ordinal
        )
    }
}