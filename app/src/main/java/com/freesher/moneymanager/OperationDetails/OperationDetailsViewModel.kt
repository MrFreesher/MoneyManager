package com.freesher.moneymanager.OperationDetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.freesher.moneymanager.OperationRepository
import com.freesher.moneymanager.data.Database
import com.freesher.moneymanager.data.Operation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OperationDetailsViewModel(application: Application) : AndroidViewModel(application){
    private val repository: OperationRepository

    init {
        val operationDao = Database.getDatabase(application).operationsDao()
        repository = OperationRepository(operationDao!!)

    }

    private val _operationDetails = MutableLiveData<Operation>()

    val operationDetails: LiveData<Operation>
        get() {
            return _operationDetails
        }

    fun getDetails(id:Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val operation = repository.getDetails(id)
            _operationDetails.postValue(operation)

        }



    }
    fun deleteOperation(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteOperation(operationDetails.value!!)
        }
    }
}