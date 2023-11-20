package com.vlados.retrofitapp.ui

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vlados.retrofitapp.app.ExerciseApp
import com.vlados.retrofitapp.databinding.BottomSheetFragmentBinding
import javax.inject.Inject

class BottomSheetFragment : BottomSheetDialogFragment() {
    private var bottomSheetBinding: BottomSheetFragmentBinding? = null
    private var selectedExerciseId: Int = 0

    @Inject
    lateinit var bottomSheetViewModel: BottomSheetViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ExerciseApp.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bottomSheetBinding = BottomSheetFragmentBinding.inflate(inflater, container, false)
        return bottomSheetBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        selectedExerciseId = arguments?.getSerializable("Exercise id") as Int
        super.onViewCreated(view, savedInstanceState)
        bottomSheetBinding?.bottomSheetButtonClose?.setOnClickListener {
            dismiss()
        }
        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            bottomSheetViewModel.weekdaysArray
        )
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        bottomSheetBinding?.spinnerWeekdays?.adapter = spinnerArrayAdapter
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        val selectedDay = bottomSheetBinding?.spinnerWeekdays?.selectedItem as String
        bottomSheetViewModel.addExerciseToPlan(selectedDay, selectedExerciseId)
    }

}