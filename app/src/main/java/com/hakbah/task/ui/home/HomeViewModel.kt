package com.hakbah.task.ui.home

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hakbah.task.api.HakbahApiRepositoryImpl
import com.hakbah.task.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject constructor(
    private val api: HakbahApiRepositoryImpl
) : ViewModel()  {

    fun getArticles()= liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getArticles()))
        }catch (e: Exception) {
            emit(Resource.error(data = null, message = "API Error"))

        }
    }

}