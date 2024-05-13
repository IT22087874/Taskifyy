package com.example.taskifyy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.taskifyy.databinding.ActivityAddTaskBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTask : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var database: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext, MyDatabase::class.java, "To_Do"
        ).build()

        binding.save.setOnClickListener {
            val title = binding.createTask.text.toString().trim()
            val priority = binding.createPriority.text.toString().trim()

            if (title.isNotEmpty() && priority.isNotEmpty()) {
                DataObject.setData(title, priority)
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title, priority))
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
