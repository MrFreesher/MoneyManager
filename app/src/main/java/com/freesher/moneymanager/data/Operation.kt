package com.freesher.moneymanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity(tableName = "operations")

data class Operation(
    val name: String,
    val description: String,
    val moneyAmount: Double,
    val operationType:String,
    val operationDate: Date
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

}