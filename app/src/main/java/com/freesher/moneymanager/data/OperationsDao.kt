package com.freesher.moneymanager.data

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
    @Query("SELECT * FROM operations WHERE id = :id")
    fun loadDetailsOfOperation(id:Int):Operation
}