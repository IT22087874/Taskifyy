package com.example.taskifyy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskifyy.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize database
        database = Room.databaseBuilder(applicationContext, myDatabase::class.java, "To_Do").build()

        // Set click listeners
        binding.addTaskButton.setOnClickListener {
            val intent = Intent(this, AddTask::class.java)
            startActivity(intent)
        }
        binding.deleteAll.setOnClickListener {
            GlobalScope.launch {
                // Delete all data from database in a coroutine
                database.dao().deleteAll()
            }
            // Refresh RecyclerView after deletion
            setRecycler()
        }

        // Set up RecyclerView
        setRecycler()
    }

    private fun setRecycler() {
        binding.recyclerView.adapter = Adapter(DataObject.getAllData())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
