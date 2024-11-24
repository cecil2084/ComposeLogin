package com.example.composelogin.api

//import android.content.Context
//import com.example.composelogin.repository.AuthRepository
//import com.example.composelogin.storage.TokenManager
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.Retrofit
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://5249-idx-studdy-1720965967101.cluster-7ubberrabzh4qqy2g4z7wgxuw2.cloudworkstations.dev/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideApiService(retrofit: Retrofit): ApiService {
//        return retrofit.create(ApiService::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideTokenManager(context: Context): TokenManager {
//        return TokenManager(context)
//    }
//
//    @Provides
//    @Singleton
//    fun provideAuthRepository(
//        apiService: ApiService,
//        tokenManager: TokenManager
//    ): AuthRepository {
//        return AuthRepository(apiService, tokenManager)
//    }
//}

//@Module
//@InstallIn(SingletonComponent::class)
//object NetworkModule {
//    private const val BASE_URL = "https://5249-idx-studdy-1720965967101.cluster-7ubberrabzh4qqy2g4z7wgxuw2.cloudworkstations.dev/"
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    @Provides
//    @Singleton
//    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
//}