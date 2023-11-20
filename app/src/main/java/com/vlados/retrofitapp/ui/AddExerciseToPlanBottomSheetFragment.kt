package com.vlados.retrofitapp.ui

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vlados.retrofitapp.app.ExerciseApp
import com.vlados.retrofitapp.databinding.AddExerciseToPlanBottomSheetFragmentBinding
import javax.inject.Inject

class AddExerciseToPlanBottomSheetFragment : BottomSheetDialogFragment() {
    private var addExerciseToPlanBottomSheetBinding: AddExerciseToPlanBottomSheetFragmentBinding? = null
    private var selectedExerciseId: Int? = null

    @Inject
    lateinit var viewModel: AddExerciseToPlanBottomSheetViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ExerciseApp.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addExerciseToPlanBottomSheetBinding = AddExerciseToPlanBottomSheetFragmentBinding.inflate(
            inflater, container, false)
        return addExerciseToPlanBottomSheetBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        selectedExerciseId = arguments?.getInt(KEY_FOR_SEARCHING_BY_ARGUMENTS, 0)
        super.onViewCreated(view, savedInstanceState)
        addExerciseToPlanBottomSheetBinding?.bottomSheetButtonClose?.setOnClickListener {
            dismiss()
        }
        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            viewModel.weekdaysArray
        )
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        addExerciseToPlanBottomSheetBinding?.spinnerWeekdays?.adapter = spinnerArrayAdapter
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        val selectedDay = addExerciseToPlanBottomSheetBinding?.spinnerWeekdays?.selectedItem as String
        viewModel.addExerciseToPlan(selectedDay, selectedExerciseId)
    }
    companion object{
        
        const val KEY_FOR_SEARCHING_BY_ARGUMENTS = "Exercise id"
        fun create(itemId: Int) = AddExerciseToPlanBottomSheetFragment().apply {
            arguments = bundleOf(KEY_FOR_SEARCHING_BY_ARGUMENTS to itemId)
        }
    }
}