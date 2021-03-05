package com.example.roomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Student::class], version=1, exportSchema = false)
abstract class StudentDB: RoomDatabase() {
    abstract fun studentDao():StudentDAO

    //companion is like a static, everyone calling this is just this
    companion object{
        private var INSTANCE: StudentDB? = null

        fun getDatabase(context: Context):StudentDB{
            synchronized(this){
                var instance= INSTANCE

                if (instance==null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        StudentDB::class.java,
                        "Student_Database"
                    ).fallbackToDestructiveMigration()
                     .build()

                }
                return instance
            }
        }
    }

}