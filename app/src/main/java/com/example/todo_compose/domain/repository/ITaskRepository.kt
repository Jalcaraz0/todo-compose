package com.example.todo_compose.domain.repository

import com.example.todo_compose.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface ITaskRepository {
    fun getTask(): Flow<List<Task>>
    suspend fun getTaskById(id: Int): Task?
    suspend fun addTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
}