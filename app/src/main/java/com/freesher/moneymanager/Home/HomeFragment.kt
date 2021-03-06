package com.freesher.moneymanager.Home

import android.os.Bundle
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



class HomeFragment : Fragment() {
    private val viewModel:HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val operationAdapter = OperationAdapter()


        operationRC.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = operationAdapter
        }
        viewModel.money.observe(viewLifecycleOwner, Observer {
            if(it != null){
                moneyAmountContent.text = it.toString()
            }

        })

        viewModel.lastFiveOperations.observe(viewLifecycleOwner, Observer {
            if(it != null){
                operationAdapter.setOperationList(it)
            }
        })

        viewModel.getMoneySum()
        viewModel.getLastFiveOperations()



    }

    override fun onStart() {
        super.onStart()
        viewModel.getLastFiveOperations()
    }


}