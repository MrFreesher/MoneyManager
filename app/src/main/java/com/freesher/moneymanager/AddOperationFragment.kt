package com.freesher.moneymanager

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
import kotlinx.android.synthetic.main.fragment_add_operation.*


class AddOperationFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_operation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                            operationDayInput.setText("$dayOfMonth / $month / $year")

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
    }


}