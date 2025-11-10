package com.example.todo_compose.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.RowId

@Entity(tableName = "tasks")
data class EntityTask(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String? = null,
    val isDone: Boolean
)