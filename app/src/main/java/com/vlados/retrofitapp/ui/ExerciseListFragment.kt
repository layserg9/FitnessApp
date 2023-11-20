package com.vlados.retrofitapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vlados.retrofitapp.app.ExerciseApp
import com.vlados.retrofitapp.data.remote.retrofit.Exercise
import com.vlados.retrofitapp.databinding.ExerciseListFragmentBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ExerciseListFragment : Fragment() {

    private var exerciseAdapter = ExerciseAdapter(::addToPlan)
    private var bindingClass: ExerciseListFragmentBinding? = null
    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var exerciseViewModel: ExerciseViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ExerciseApp.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingClass = ExerciseListFragmentBinding.inflate(inflater, container, false)
        return bindingClass?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO реализовать обращение к аргументам (id)
        bindingClass?.elRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        bindingClass?.elRecyclerView?.adapter = exerciseAdapter
        bindingClass?.elRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                    exerciseViewModel.updateExerciseList()
                }
            }
        })
    }

    @SuppressLint("CheckResult")
    override fun onStart() {
        super.onStart()
        exerciseViewModel.updateExerciseList()
        val disposable = exerciseViewModel.getExerciseListFlow()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("FITNESS_APP", "$it")
                    exerciseAdapter.submitList(it)
                }
            )
        compositeDisposable.add(disposable)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

//TODO в onStop сделать отписку от потока


    override fun onDestroyView() {
        super.onDestroyView()
        bindingClass = null
    }

    private fun addToPlan(item: Exercise) {
        val bottomSheetFragment = BottomSheetFragment()
        val bundle = Bundle()
        bundle.putInt("Exercise id", item.id)
        bottomSheetFragment.arguments = bundle
        bottomSheetFragment.show(childFragmentManager, "my bottom sheet")
    }
}