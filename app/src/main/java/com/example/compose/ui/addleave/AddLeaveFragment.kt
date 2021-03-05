package com.example.compose.ui.addleave

import androidx.fragment.app.Fragment
import com.example.compose.ui.commom.AddLeaveActionType
import com.google.android.material.datepicker.MaterialDatePicker

class AddLeaveFragment : Fragment() {
    private fun handleSurveyAction(questionId: Int, actionType: AddLeaveActionType) {
        when (actionType) {
            AddLeaveActionType.PICK_DATE -> showDatePicker(questionId)
        }
    }
    private fun showDatePicker(questionId: Int) {
        val picker = MaterialDatePicker.Builder.datePicker().build()
        activity?.let {
            picker.show(it.supportFragmentManager, picker.toString())
//            picker.addOnPositiveButtonClickListener {
//                viewModel.onDatePicked(questionId, picker.headerText)
//            }
        }
    }
}