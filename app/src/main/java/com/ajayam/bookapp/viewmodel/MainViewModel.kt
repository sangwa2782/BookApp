package com.ajayam.bookapp.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajayam.bookapp.data.DataRepository
import com.ajayam.bookapp.model.ResponseData
import com.google.gson.Gson
import kotlinx.coroutines.*

class MainViewModel(application: Application, private val dataRepository: DataRepository) :
    ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val courseData: MutableLiveData<ResponseData> = MutableLiveData<ResponseData>()



    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    //to get user
    fun getCoursesData() {

       // loading.value=true

        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = dataRepository.getCoursesData()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.e("onSuccess1", Gson().toJson(response.body()))
                    courseData.postValue(response.body())
                    loading.value = false
                } else {
                    Log.e("Error1", Gson().toJson(response.message()))
                    onError("Error : ${response.message()} ")
                    loading.value = false
                }
            }
        }
    }






    //to print error
    private fun onError(message: String) {

        errorMessage.postValue(message)
        Log.e("TAG", "onError(): " + message)
        loading.postValue(false)
    }


    /**Comments
     * ViewModelScope is a Kotlin extension provided by the Android Architecture Components
     * library. It allows developers to easily manage the lifecycle of coroutines launched
     * within the scope of a ViewModel. This is particularly useful for long-running
     * background tasks that should be cancelled when the ViewModel is destroyed,
     * as it ensures that the coroutines are cancelled and their resources are properly
     * cleaned up.
     * */
}