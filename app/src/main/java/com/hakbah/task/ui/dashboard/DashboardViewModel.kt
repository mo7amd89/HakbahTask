package com.hakbah.task.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hakbah.task.data.Resource
import com.hakbah.task.db.HakbahDbRepositoryImpl
import com.hakbah.task.db.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dbRepositoryImpl: HakbahDbRepositoryImpl
) : ViewModel() {

    fun getPosts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dbRepositoryImpl.getPosts()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = "DataBase Error"))

        }
    }

    fun addPost(post: Post) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dbRepositoryImpl.addPost(post)))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.error(data = null, message = "DataBase Error"))

        }
    }
    fun deletePost(post: Post) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dbRepositoryImpl.deletePost(post)))
        } catch (e: Exception) {

            emit(Resource.error(data = null, message = "DataBase Error"))

        }
    }

}


