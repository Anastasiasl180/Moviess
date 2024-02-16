package com.example.moviess.di

import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.moviess.common.Constants
import com.example.moviess.data.remote.MovieApi
import com.example.moviess.data.repository.AuthRepositoryImpl
import com.example.moviess.data.repository.MovieRepositoryImpl
import com.example.moviess.domain.repository.AuthRepository
import com.example.moviess.domain.repository.MovieRepository
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton

    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSignInClient(context: Context): SignInClient {
        return Identity.getSignInClient(context)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApi): MovieRepository {
        return MovieRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()


    @Provides
    @Singleton
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesContext(application: Application): Context {
        return application
    }

}