package com.freesher.moneymanager.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface OperationsDao {
    @Insert
    fun insertOperation(operation: Operation)
    @Query("SELECT * FROM operations ORDER BY date(operationDate) DESC LIMIT 5")
    fun loadLastFiveOperations(): List<Operation>
    @Query("SELECT * FROM operations")
    fun loadAllOperations():List<Operation>
    @Query("SELECT * FROM operations WHERE id = :operationId")
    fun loadDetailsOfOperation(operationId:Int):Operation
    @Query("SELECT SUM(moneyAmount) from operations")
    fun calculateCurrentMoney():Double
}