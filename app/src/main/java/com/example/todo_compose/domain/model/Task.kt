package com.example.todo_compose.domain.model

data class Task(
    val id: Int,
    val title: String,
    val description: String? = null,
    val isDone: Boolean
)
