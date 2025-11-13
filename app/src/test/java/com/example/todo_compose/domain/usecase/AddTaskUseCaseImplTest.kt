package com.example.todo_compose.domain.usecase

import com.example.todo_compose.domain.model.Task
import com.example.todo_compose.domain.model.usecase.AddTaskUseCase
import com.example.todo_compose.domain.model.usecase.AddTaskUseCaseImpl
import com.example.todo_compose.domain.repository.ITaskRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AddTaskUseCaseImplTest {
    private lateinit var repository: ITaskRepository
    private lateinit var useCase: AddTaskUseCase

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        useCase = AddTaskUseCaseImpl(repository)
    }

    @Test
    fun `returns failure when title is blank`() = runTest {
        val result = useCase(Task(id = 123, title = "   ", description = "x", isDone = false))
        assertTrue(result.isFailure)
        val ex = result.exceptionOrNull()
        assertTrue(ex is IllegalArgumentException)
    }

    @Test
    fun `calls repository and returns success when title is valid`() = runTest {
        coEvery { repository.addTask(any()) } returns Unit

        val result = useCase(Task(id = 999, title = "Buy milk", description = "2L", isDone = false))

        assertTrue(result.isSuccess)
        coVerify(exactly = 1) {
            repository.addTask(withArg { t ->
                assertEquals("Buy milk", t.title)
                assertEquals("2L", t.description)
                assertEquals(0, t.id) // normalizado para autogenerado
            })
        }
    }

}