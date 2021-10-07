package com.hakbah.task.api

import com.hakbah.task.data.Article

interface HakbahApiRepository {

    suspend fun getArticles(): Article
}