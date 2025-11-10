package com.example.todo_compose.di

import android.content.Context
import androidx.room.Room
import com.example.todo_compose.data.local.TaskDao
import com.example.todo_compose.data.local.TaskDatabase
import com.example.todo_compose.data.repository.ITaskRepositoryImpl
import com.example.todo_compose.domain.repository.ITaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TaskDatabase =
        Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "tasks.db"
        )
            .fallbackToDestructiveMigration() // destruye y recrea si cambia versión (útil en dev)
            .build()

    @Provides
    fun provideTaskDao(db: TaskDatabase): TaskDao = db.taskDao()

    @Provides
    @Singleton
    fun provideTaskRepository(dao: TaskDao): ITaskRepository =
        ITaskRepositoryImpl(dao)
}