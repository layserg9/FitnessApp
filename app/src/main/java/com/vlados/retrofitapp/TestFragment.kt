package com.vlados.retrofitapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vlados.myownbottomnavigation.ExerciseAdapter
import com.vlados.retrofitapp.databinding.ExerciseListFragmentBinding

class TestFragment : Fragment() {
    private var exerciseAdapter = ExerciseAdapter()
    private var bindingClass: ExerciseListFragmentBinding? = null


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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingClass = null
    }

    companion object {

    }
}