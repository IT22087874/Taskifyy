package com.example.taskifyy

import android.content.Intent
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize database
        database = Room.databaseBuilder(applicationContext, myDatabase::class.java, "To_Do").build()

        // Set click listeners
        add.setOnClickListener {
            val intent = Intent(this, AddTask::class.java)
            startActivity(intent)
        }
        deleteAll.setOnClickListener {
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
        recycler_view.adapter = RecyclerView.Adapter(DataObject.getAllData())
        recycler_view.layoutManager = LinearLayoutManager(this)
    }
}