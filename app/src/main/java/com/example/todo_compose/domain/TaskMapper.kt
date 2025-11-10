package com.example.todo_compose.domain

import com.example.todo_compose.data.local.EntityTask
import com.example.todo_compose.domain.model.Task

object TaskMapper {

    fun EntityTask.toDomain(): Task {
        return Task(
            id = id,
            title = title,
            description = description,
            isDone = isDone
        )
    }

    fun Task.toEntity(): EntityTask {
        return EntityTask(
            id = id,
            title = title,
            description = description,
            isDone = isDone
        )
    }
}