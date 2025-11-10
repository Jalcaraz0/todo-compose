package com.example.todo_compose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getTasks(): Flow<List<EntityTask>>
    @Query("SELECT * FROM tasks WHERE id = :id LIMIT 1")
   suspend  fun getTaskById(id: Int): EntityTask?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: EntityTask): Long
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: EntityTask): Int
    @Delete
    suspend fun delete(entity: EntityTask): Int
}