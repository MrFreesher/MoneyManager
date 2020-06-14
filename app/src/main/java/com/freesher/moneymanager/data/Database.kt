package com.freesher.moneymanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Operation::class], version = 1)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun operationsDao(): OperationsDao?

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: com.freesher.moneymanager.data.Database? = null

        fun getDatabase(context: Context): com.freesher.moneymanager.data.Database {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    com.freesher.moneymanager.data.Database::class.java,
                    "money_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}