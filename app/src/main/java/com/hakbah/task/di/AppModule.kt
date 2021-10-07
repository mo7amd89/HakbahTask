package com.hakbah.task.di

import android.app.Application
import androidx.room.Room
import com.hakbah.task.api.HakbahApi
import com.hakbah.task.db.HakbahDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl(HakbahApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideHakbahApi(retrofit: Retrofit):HakbahApi=retrofit.create(HakbahApi::class.java)


    @Provides
    @Singleton
    fun provideDatabase(application: Application):HakbahDataBase= Room.databaseBuilder(application,HakbahDataBase::class.java,"hakbah_dataBase").build()
}