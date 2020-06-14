package com.freesher.moneymanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.freesher.moneymanager.data.Operation
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val c = Calendar.getInstance().time
        val opList = listOf<Operation>(
            Operation(1,"Operation 1",20.0, c),
            Operation(2,"Operation 2",24.3, c),
            Operation(3,"Operation 3",-10.33, c)
        )
        val operationAdapter = OperationAdapter()
        val dividerItemDecoration = DividerItemDecoration(
            operationRC.getContext(),
            LinearLayoutManager.VERTICAL
        )

        operationRC.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = operationAdapter
        }
        operationRC.addItemDecoration(dividerItemDecoration)
        operationAdapter.setOperationList(opList)


    }


}