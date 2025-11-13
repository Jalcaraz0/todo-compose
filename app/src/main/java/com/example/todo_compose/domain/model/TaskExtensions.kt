package com.example.todo_compose.domain.model

fun Task.clean(): Task {
    return this.copy(
        title = title.trim(),
        description = description?.trim()?.ifBlank { null },
        id = 0
    )
}