package com.freesher.moneymanager.OperationList

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

class OperationListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: OperationRepository

    init {
        val operationDao = Database.getDatabase(application).operationsDao()
        repository = OperationRepository(operationDao!!)

    }

    private val _operations = MutableLiveData<List<Operation>>()

    val operations: LiveData<List<Operation>>
        get() {
            return _operations
        }

    fun getAllOperations() {

        viewModelScope.launch(Dispatchers.IO) {
            val allOperations = repository.getAllOperations()
            _operations.postValue(allOperations)

        }


    }
}