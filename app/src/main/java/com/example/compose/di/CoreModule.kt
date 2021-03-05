package com.example.compose.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.core.Preferences
import com.example.compose.common.TOKEN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {
    @Provides
    fun providesDataStore(@ApplicationContext app: Context): DataStore<Preferences> {
        return app.createDataStore(name = TOKEN)
    }
}