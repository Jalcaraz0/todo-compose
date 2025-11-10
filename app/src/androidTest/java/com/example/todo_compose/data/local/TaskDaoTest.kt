package com.example.todo_compose.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskDaoTest {

    private lateinit var db: TaskDatabase
    private lateinit var dao: TaskDao

    @Before
    fun setup() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, TaskDatabase::class.java)
            // Solo para tests: permite queries en main thread
            .allowMainThreadQueries()
            .build()
        dao = db.taskDao()
    }

    @After
    fun tearDown() {
        // Cierra solo si se alcanzó a inicializar
        if (this::db.isInitialized) {
            db.close()
        }
    }

    @Test
    fun insert_and_list_tasks() = runTest {
        // Given
        val entity = EntityTask(
            id = 0, // autogenerado
            title = "Test",
            description = "Desc",
            isDone = false
        )

        // When
        val newId = dao.insert(entity)
        // Then
        assert(newId > 0)

        val items = dao.getTasks().first()
        assertEquals(1, items.size)
        assertEquals("Test", items.first().title)
    }

    @Test
    fun get_task_by_id_returns_inserted_item() = runTest {
        val id = dao.insert(
            EntityTask(id = 0, title = "ById", description = null, isDone = false)
        ).toInt()

        val found = dao.getTaskById(id)
        assertNotNull(found)
        assertEquals("ById", found?.title)
        assertEquals(id, found?.id)
    }

    @Test
    fun update_task_changes_are_persisted() = runTest {
        val id = dao.insert(
            EntityTask(id = 0, title = "Old", description = null, isDone = false)
        ).toInt()

        val updated = EntityTask(id = id, title = "New", description = "Desc", isDone = true)
        val rows = dao.update(updated)
        assertEquals(1, rows)

        val reloaded = dao.getTaskById(id)
        assertEquals("New", reloaded?.title)
        assertEquals(true, reloaded?.isDone)
        assertEquals("Desc", reloaded?.description)
    }

    @Test
    fun delete_task_removes_from_list() = runTest {
        val id = dao.insert(
            EntityTask(id = 0, title = "ToDelete", description = null, isDone = false)
        ).toInt()

        val entity = dao.getTaskById(id)!!
        val rows = dao.delete(entity)
        assertEquals(1, rows)

        val items = dao.getTasks().first()
        assertEquals(0, items.size)
    }
}