package com.example.taskifyy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.taskifyy.databinding.ActivityUpdateTaskBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateTask : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var database: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext, MyDatabase::class.java, "To_Do"
        ).build()

        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority
            binding.createTitle.setText(title)
            binding.createPriority.setText(priority)

            binding.deleteButton.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(
                            pos + 1,
                            binding.createTitle.text.toString(),
                            binding.createPriority.text.toString()
                        )
                    )
                }
                myIntent()
            }

            binding.updateButton.setOnClickListener {
                DataObject.updateData(
                    pos,
                    binding.createTitle.text.toString(),
                    binding.createPriority.text.toString()
                )
                GlobalScope.launch {
                    database.dao().updateTask(
                        Entity(
                            pos + 1, binding.createTitle.text.toString(),
                            binding.createPriority.text.toString()
                        )
                    )
                }
                myIntent()
            }
        }
    }

    private fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
