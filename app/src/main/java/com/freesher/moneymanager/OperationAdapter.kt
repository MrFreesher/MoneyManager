package com.freesher.moneymanager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.freesher.moneymanager.data.Operation
import kotlinx.android.synthetic.main.simple_item.view.*

class OperationAdapter : RecyclerView.Adapter<OperationAdapter.OperationViewHolder>() {
    private val operationsList = mutableListOf<Operation>()
    class OperationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val operationNameContent = itemView.operationName
        private val operationMoneyAmountConent = itemView.operationMoneyAmount
        fun bindValues(operationName: String, operationMoneyAmount: Double) {
            operationNameContent.text = operationName
            operationMoneyAmountConent.text = operationMoneyAmount.toString()
        }
        fun setOnClickListener(id:Int){
            itemView.setOnClickListener {
                val bundle = Bundle()
                Log.e("myapp",id.toString())
                bundle.putInt("operationId",id)
                val transition = Navigation.createNavigateOnClickListener(R.id.operationDetailsFragment,bundle).onClick(itemView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.simple_item,parent,false)
        return OperationViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return operationsList.size
    }

    override fun onBindViewHolder(holder: OperationViewHolder, position: Int) {
        val item = operationsList[position]
        holder.bindValues(item.name,item.moneyAmount)
        holder.setOnClickListener(item.id!!)
    }

    fun setOperationList(newOperations:List<Operation>){
        operationsList.clear()
        operationsList.addAll(newOperations)
        notifyDataSetChanged()
    }

}