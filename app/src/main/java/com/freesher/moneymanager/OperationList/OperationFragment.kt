package com.freesher.moneymanager.OperationList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.freesher.moneymanager.OperationAdapter
import com.freesher.moneymanager.R
import kotlinx.android.synthetic.main.fragment_home.*


class OperationFragment : Fragment() {
    private val viewModel: OperationListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_operation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val operationAdapter = OperationAdapter()


        operationRC.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = operationAdapter
        }
        viewModel.operations.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                operationAdapter.setOperationList(it!!)
            }
        })
        viewModel.getAllOperations()

        viewModel.getAllOperations()

    }

}