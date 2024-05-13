package com.example.taskifyy

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LaunchScreen : AppCompatActivity() {
    private lateinit var database: MyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launch_screen)
        database = Room.databaseBuilder(
            applicationContext, MyDatabase::class.java, "To_Do"
        ).build()
        GlobalScope.launch {
            DataObject.listdata = database.dao().getTasks() as MutableList<TaskInfo>
        }
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 2000)
    }

}