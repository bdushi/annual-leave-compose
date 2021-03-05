package com.example.compose.di

import com.example.compose.data.source.AuthDataSource
import com.example.compose.data.source.LeaveDataSource
import com.example.compose.data.source.LeaveTypesDataSource
import com.example.compose.data.source.local.AuthLocalDataSource
import com.example.compose.data.source.remote.AuthRemoteDataSource
import com.example.compose.data.source.remote.LeaveRemoteDataSource
import com.example.compose.data.source.remote.LeaveTypesRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun provideAuthRemoteDataSource(authDataSource: AuthRemoteDataSource): AuthDataSource

    @Binds
    abstract fun provideAuthLocalDataSource(authDataSource: AuthLocalDataSource): AuthDataSource

    @Binds
    abstract fun provideLeaveRemoteDataSource(leaveRemoteDataSource: LeaveRemoteDataSource): LeaveDataSource

    @Binds
    abstract fun provideLeaveTypesRemoteDataSource(leaveTypesRemoteDataSource: LeaveTypesRemoteDataSource): LeaveTypesDataSource
}