package com.vlados.retrofitapp

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
import com.vlados.myownbottomnavigation.ExerciseAdapter
import com.vlados.retrofitapp.app.ExerciseApp
import com.vlados.retrofitapp.databinding.ExerciseListFragmentBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ExerciseListFragment : Fragment() {

    private var exerciseAdapter = ExerciseAdapter()
    private var bindingClass: ExerciseListFragmentBinding? = null

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
        bindingClass?.elRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        bindingClass?.elRecyclerView?.adapter = exerciseAdapter
        bindingClass?.elRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                if((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition>=0){
                    exerciseViewModel.updateExerciseList()
                }
            }
        })
    }

    @SuppressLint("CheckResult")
    override fun onStart() {
        super.onStart()
        exerciseViewModel.updateExerciseList()
        exerciseViewModel.getExerciseListFlow()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("VLADIK_APP", "$it")
                    exerciseAdapter.submitList(it)
                    Log.d("VLADIK_APP", "Поток пришел")
                }
            )
    }

//TODO в onStop сделать отписку от потока



    override fun onDestroyView() {
        super.onDestroyView()
        bindingClass = null
    }

}