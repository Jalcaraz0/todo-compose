package com.example.todo_compose.domain.model.usecase

import com.example.todo_compose.domain.model.Task

interface AddTaskUseCase {

    suspend operator fun invoke(task: Task): Result<Unit>
}