package com.freesher.moneymanager.OperationDetails

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
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
        setHasOptionsMenu(true)
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
                moneyAmountContent.text = it?.moneyAmount.toInt().toString()


            }
        })
        val arguments = arguments
        val id:Int = arguments?.getInt("operationId")!!
        viewModel.getDetails(id)


    }

    override fun onStart() {
        super.onStart()
        val arguments = arguments
        val id:Int = arguments?.getInt("operationId")!!
        viewModel.getDetails(id)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.deleteOperation).setVisible(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteOperation ->{
                AlertDialog.Builder(requireContext()).also {
                    it.setTitle("Remove")
                    it.setMessage("Are you sure to remove this operation from your list")
                    it.setPositiveButton(
                        "Yes"
                    ) { dialog, _ ->


                        dialog.dismiss()

                        viewModel.deleteOperation()

                        Toast.makeText(requireContext(),"Operation was removed from your list",Toast.LENGTH_SHORT).show()
                    }
                    it.setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
                }.show()
            }
        }
        return super.onOptionsItemSelected(item)

    }
}