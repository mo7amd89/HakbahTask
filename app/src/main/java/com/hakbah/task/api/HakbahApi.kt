package com.hakbah.task.api

import com.hakbah.task.data.Article
import retrofit2.http.GET

interface HakbahApi {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }
    @GET("top-headlines?country=sa&apiKey=71f81d9d7d054d34836780e266e2e2d9&category=technology")
    suspend fun getArticles():Article
}