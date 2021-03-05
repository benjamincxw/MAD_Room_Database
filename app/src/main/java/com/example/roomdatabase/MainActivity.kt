package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.roomdatabase.data.Student
import com.example.roomdatabase.data.StudentDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd: Button  = findViewById(R.id.addStudent)

        btnAdd.setOnClickListener(){
            val newRec: Student = Student(0, "bucky", "RSF")

            CoroutineScope(IO).launch{
                val studentDAO = StudentDB.getDatabase(application).studentDao()
                studentDAO.addStudent(newRec)
            }
        }

        //get the retrieve button once its clicked
        val btnRetrieve:Button = findViewById(R.id.btnRetrieve)
        btnRetrieve.setOnClickListener(){
            CoroutineScope(Main).launch {
                var name:String =""
                val studentDAO = StudentDB.getDatabase(application).studentDao()
                val studentList: Array<Student> = studentDAO.getAllStudent()
                if (studentList!=null){
                    for (s:Student in studentList) {
                        name+=s.name+"\n"

                    }
                }
                val txtData:TextView=findViewById(R.id.txtData)
                txtData.setText(name)
            }
        }
    }
}