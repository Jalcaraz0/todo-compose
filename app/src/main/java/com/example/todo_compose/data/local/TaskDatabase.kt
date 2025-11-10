package com.example.todo_compose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [EntityTask::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}