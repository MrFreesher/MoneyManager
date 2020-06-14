package com.freesher.moneymanager.Home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.freesher.moneymanager.OperationRepository
import com.freesher.moneymanager.data.Database
import com.freesher.moneymanager.data.Operation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: OperationRepository

    init {
        val operationDao = Database.getDatabase(application).operationsDao()
        repository = OperationRepository(operationDao!!)

    }

    private val _money = MutableLiveData<Double>()
    val money : LiveData<Double>
    get() {return _money}
    private val _lastFiveOperations = MutableLiveData<List<Operation>>()
    val lastFiveOperations : LiveData<List<Operation>>
    get() {return _lastFiveOperations}



    fun getMoneySum(){
        viewModelScope.launch(Dispatchers.IO){
            val moneySum = repository.calculateAllMoney()
            _money.postValue(moneySum)
        }
    }
    fun getLastFiveOperations(){
        viewModelScope.launch(Dispatchers.IO){
            val operations = repository.getLastFiveOperations()
            _lastFiveOperations.postValue(operations)
        }
    }
}