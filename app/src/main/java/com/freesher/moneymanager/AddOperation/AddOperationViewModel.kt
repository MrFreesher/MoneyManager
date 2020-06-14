package com.freesher.moneymanager.AddOperation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freesher.moneymanager.OperationRepository
import com.freesher.moneymanager.data.Database
import com.freesher.moneymanager.data.Operation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull

class AddOperationViewModel(@NotNull application: Application) : AndroidViewModel(application) {

    private val repository:OperationRepository
    init {
        val operationDao = Database.getDatabase(application).operationsDao()
        repository = OperationRepository(operationDao!!)

    }
    fun insertOperation(operation:Operation) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertOperation(operation)
    }
}