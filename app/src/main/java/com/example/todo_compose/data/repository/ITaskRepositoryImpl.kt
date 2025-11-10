package com.example.todo_compose.data.repository

import androidx.compose.animation.defaultDecayAnimationSpec
import com.example.todo_compose.data.local.TaskDao
import com.example.todo_compose.domain.TaskMapper.toDomain
import com.example.todo_compose.domain.TaskMapper.toEntity
import com.example.todo_compose.domain.model.Task
import com.example.todo_compose.domain.repository.ITaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ITaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao
) :ITaskRepository {

    override fun getTask(): Flow<List<Task>> =
        dao.getTasks().map { list -> list.map { it.toDomain() }
    }

    override suspend fun getTaskById(id: Int): Task? =
        dao.getTaskById(id)?.toDomain()

    override suspend fun addTask(task: Task) {
        dao.insert(task.toEntity())
    }

    override suspend fun updateTask(task: Task) {
        dao.update(task.toEntity())
    }

    override suspend fun deleteTask(task: Task) {
        dao.delete(task.toEntity())
    }
}