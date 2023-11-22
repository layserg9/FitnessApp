package com.vlados.retrofitapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vlados.retrofitapp.app.ExerciseApp
import com.vlados.retrofitapp.databinding.TrainingPlanFragmentBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrainingPlanFragment : Fragment() {
    private val trainingPlanAdapter: TrainingPlanAdapter = TrainingPlanAdapter()
    private var binding: TrainingPlanFragmentBinding? = null
    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var trainingPlanViewModel: TrainingPlanViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ExerciseApp.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TrainingPlanFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.trainingPlanRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.trainingPlanRecyclerView?.adapter = trainingPlanAdapter
    }

    override fun onStart() {
        super.onStart()
        val disposable = trainingPlanViewModel.getTrainingPlanList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("FITNESS_APP", "$it")
                trainingPlanAdapter.submitList(it)
            }
            compositeDisposable.add(disposable)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}