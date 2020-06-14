package com.freesher.moneymanager.OperationDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.freesher.moneymanager.R
import kotlinx.android.synthetic.main.fragment_operation_details.*
import java.text.SimpleDateFormat


class OperationDetailsFragment : Fragment() {

    private val viewModel:OperationDetailsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_operation_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.operationDetails.observe(viewLifecycleOwner, Observer {
            if(it != null){
                nameContent.text = it?.name
                descriptionContent.text = it?.description
                operationTypeContent.text = it?.operationType
                val format = SimpleDateFormat("dd-MM-yyyy hh:mm")
                val dateString = format.format(it.operationDate)
                operationDateContent.text = dateString
                moneyAmountContent.text = it?.moneyAmount.toString()


            }
        })
        val arguments = arguments
        val id:Int = arguments?.getInt("operationId")!!
        viewModel.getDetails(id)


        Log.e("myapp",id.toString())
    }

    override fun onStart() {
        super.onStart()
        val arguments = arguments
        val id:Int = arguments?.getInt("operationId")!!
        viewModel.getDetails(id)
    }


}