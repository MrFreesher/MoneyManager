package com.freesher.moneymanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.freesher.moneymanager.data.Operation
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class OperationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_operation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val c = Calendar.getInstance().time
        val opList = listOf<Operation>(
            Operation(1,"Operation 1","Some description",20.0, c),
            Operation(2,"Operation 2","Some description",24.3, c),
            Operation(3,"Operation 3","Some description",-10.33, c)
        )
        val operationAdapter = OperationAdapter()


        operationRC.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            adapter = operationAdapter
        }

        operationAdapter.setOperationList(opList)
    }

}