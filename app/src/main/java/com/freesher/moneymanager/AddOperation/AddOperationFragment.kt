package com.freesher.moneymanager.AddOperation

import android.app.Application
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.freesher.moneymanager.R
import com.freesher.moneymanager.data.Operation
import kotlinx.android.synthetic.main.fragment_add_operation.*
import java.text.SimpleDateFormat
import java.util.*


class AddOperationFragment : Fragment() {
    private lateinit var viewModel: AddOperationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_operation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this).get(AddOperationViewModel::class.java)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.operationTypes,
            R.layout.spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown)
            operationTypeInput.adapter = adapter
        }
        operationDayInput.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                val datePickerDialog = DatePickerDialog(
                    requireContext(), object : DatePickerDialog.OnDateSetListener {
                        override fun onDateSet(
                            view: DatePicker?,
                            year: Int,
                            month: Int,
                            dayOfMonth: Int
                        ) {
                            operationDayInput.setText("$dayOfMonth/$month/$year")

                        }
                    }, 2020, 14, 6

                )
                datePickerDialog.show()
            }
        }
        operationTimeInput.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                val timePickerDialog =
                    TimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener {
                        override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                            operationTimeInput.setText("$hourOfDay:$minute")
                        }


                    }, 0, 0, true)

                timePickerDialog.show()
            }
        }
        addOperationBtn.setOnClickListener {
            val operationDate =
                createDate(operationDayInput.text.toString(), operationTimeInput.text.toString())
            val operationName = operationNameInput.text.toString()
            val operationDescription = operationDescriptionInput.text.toString()
            val operationMoneyAmount = operationMoneyAmountInput.text.toString().toDouble()
            val operationType = operationTypeInput.selectedItem.toString()
            val isNameValidate = isNameValidate(operationName)
            val isDescriptionValidate = isDescriptionValidate(operationDescription)
            val isMoneyValidate = isMoneyAmountValidate(operationMoneyAmount)

            if (isNameValidate && isMoneyValidate && isDescriptionValidate) {
                val operation = Operation(
                    id = null,
                    name = operationName,
                    description = operationDescription,
                    moneyAmount = operationMoneyAmount,
                    operationDate = operationDate,
                    operationType = operationType
                )
                viewModel.insertOperation(operation)
                clearAllFields()
            }
        }
    }


    fun isNameValidate(name: String): Boolean {
        if (name.isEmpty()) {
            return false
        } else {
            return true
        }
    }

    fun isDescriptionValidate(description: String): Boolean {
        if (description.isEmpty()) {
            return false
        } else {
            return true
        }
    }

    fun isMoneyAmountValidate(moneyAmount: Double): Boolean {
        if (moneyAmount.toString().isEmpty()) {
            return false
        } else {
            return true
        }
    }

    fun createDate(dateString: String, timeString: String): Date {
        val date = SimpleDateFormat("dd/MM/yyyy hh:mm").parse("$dateString $timeString")
        return date
    }

    fun clearAllFields() {
        operationDayInput.text.clear()
        operationTimeInput.text.clear()
        operationDescriptionInput.text.clear()
        operationMoneyAmountInput.text.clear()
        operationNameInput.text.clear()
    }


}