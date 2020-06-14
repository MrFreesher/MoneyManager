package com.freesher.moneymanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Operation::class], version = 1)
@TypeConverters(Converters::class)
abstract class Data : RoomDatabase() {
    abstract fun operationsDao(): OperationsDao?
}