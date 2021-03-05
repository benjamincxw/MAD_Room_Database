package com.example.roomdatabase.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//this file is actually data accessed object, where you put your sql query here
@Dao
interface StudentDAO {
//add suspend to work the query into a background
    @Insert
    suspend fun addStudent(student:Student)

    @Query("select * from student_table")
    suspend fun getAllStudent() :Array<Student>

}