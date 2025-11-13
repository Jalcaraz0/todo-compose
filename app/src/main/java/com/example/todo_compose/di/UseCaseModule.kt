package com.example.todo_compose.di

import com.example.todo_compose.domain.model.usecase.AddTaskUseCase
import com.example.todo_compose.domain.model.usecase.AddTaskUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindAddTaskUseCase(
        impl: AddTaskUseCaseImpl
    ): AddTaskUseCase
}