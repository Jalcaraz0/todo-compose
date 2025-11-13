package com.example.todo_compose.domain.model.usecase

import com.example.todo_compose.domain.model.Task
import com.example.todo_compose.domain.model.clean
import com.example.todo_compose.domain.repository.ITaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddTaskUseCaseImpl @Inject constructor(
    private val repository: ITaskRepository
) : AddTaskUseCase {

    override suspend fun invoke(task: Task): Result<Unit> {
        val title = task.title.trim()
        if (title.isEmpty()) {
            return Result.failure(IllegalArgumentException("Title must not be empty"))
        }
        val normalized = task.clean()

        return runCatching {
            repository.addTask(normalized)
        }
    }
}