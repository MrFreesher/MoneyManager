package com.freesher.moneymanager

import androidx.lifecycle.LiveData
import com.freesher.moneymanager.data.Operation
import com.freesher.moneymanager.data.OperationsDao

class OperationRepository(private val operationsDao: OperationsDao) {


    fun getAllOperations(): LiveData<List<Operation>> {
        return operationsDao.loadAllOperations()
    }

    fun getLastFiveOperations(): LiveData<List<Operation>> {
        return operationsDao.loadLastFiveOperations()
    }

    fun getDetails(id: Int): LiveData<Operation> {
        return operationsDao.loadDetailsOfOperation(id)
    }

    suspend fun insertOperation(operation: Operation) {
        operationsDao.insertOperation(operation)
    }

}