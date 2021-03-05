package com.example.compose.di

import com.example.compose.common.SERVER_URL
import com.example.compose.data.source.remote.service.AuthService
import com.example.compose.data.source.remote.service.LeaveService
import com.example.compose.data.source.remote.service.LeaveTypesService
import com.example.compose.interceptor.*
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModuleNetworkModule {
    @Provides
    @Singleton
    fun providerRetrofit(authInterceptor: AuthInterceptor, authorizationInterceptor: AuthorizationInterceptor): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(SERVER_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(authInterceptor)
//                    .addInterceptor(authorizationInterceptor)
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .addSerializationExclusionStrategy(object : ExclusionStrategy {
                            override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                                return false
                            }

                            override fun shouldSkipField(f: FieldAttributes?): Boolean {
                                return f?.getAnnotation(Exclude::class.java) != null
                            }

                        })
                        .registerTypeAdapter(LocalDateTime::class.java,  DateTimeSerializer())
                        // .registerTypeAdapter(WorkStation::class.java, WorkStationDeserializer())
                        // .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                        .setLenient()
                        .setPrettyPrinting()
                        .create()
                )
            )
            .build()
    }

    @Provides
    @Reusable
    fun errorHandler(retrofit: Retrofit): ErrorHandler {
        return ErrorHandler(retrofit)
    }

    @Provides
    @Reusable
    fun authService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Reusable
    fun leaveTypesService(retrofit: Retrofit): LeaveTypesService {
        return retrofit.create(LeaveTypesService::class.java)
    }

    @Provides
    @Reusable
    fun leaveService(retrofit: Retrofit): LeaveService {
        return retrofit.create(LeaveService::class.java)
    }

}