package com.example.taskifyy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "To_Do")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0, // Remove id parameter from the constructor
    var title: String,
    var priority: String
)

