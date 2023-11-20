package com.vlados.retrofitapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vlados.retrofitapp.databinding.TestListFragmentBinding

class TestFragment : Fragment() {

    private var bindingClass: TestListFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingClass = TestListFragmentBinding.inflate(inflater, container, false)
        return bindingClass?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }



//TODO в onStop сделать отписку от потока



    override fun onDestroyView() {
        super.onDestroyView()
        bindingClass = null
    }


    /////////////////////////////////////////////////////
//    private var exerciseAdapter = ExerciseAdapter()
//    private var bindingClass: TestListFragmentBinding? = null
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        bindingClass = TestListFragmentBinding.inflate(inflater, container, false)
//        return bindingClass?.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        bindingClass?.tlRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
//        bindingClass?.tlRecyclerView?.adapter = exerciseAdapter
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        bindingClass = null
//    }
//
//    companion object {
//
//    }
}