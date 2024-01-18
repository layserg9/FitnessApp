package com.vlados.retrofitapp.ui

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateOf
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vlados.retrofitapp.app.ExerciseApp
import com.vlados.retrofitapp.databinding.AddExerciseToPlanBottomSheetFragmentBinding
import javax.inject.Inject

class AddExerciseToPlanBottomSheetFragment : BottomSheetDialogFragment() {
    private var viewBinding: AddExerciseToPlanBottomSheetFragmentBinding? = null
    private var selectedExerciseId: Int? = null
    private val trainingDay = mutableStateOf("")

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
        viewBinding = AddExerciseToPlanBottomSheetFragmentBinding.inflate(
            inflater, container, false
        )
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        selectedExerciseId = arguments?.getInt(KEY_FOR_SEARCHING_BY_ARGUMENTS, 0)
        super.onViewCreated(view, savedInstanceState)
        viewBinding?.composeView?.setContent {
            ComposeBottomSheet(
                viewModel.weekdaysArray,
                ::dismiss,
                trainingDay
            )
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        viewModel.addExerciseToPlan(trainingDay.value, selectedExerciseId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    companion object {
        const val KEY_FOR_SEARCHING_BY_ARGUMENTS = "Exercise id"
        fun create(itemId: Int) = AddExerciseToPlanBottomSheetFragment().apply {
            arguments = bundleOf(KEY_FOR_SEARCHING_BY_ARGUMENTS to itemId)
        }
    }
}