package com.ajayam.mygit.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajayam.bookapp.data.DataRepository
import com.ajayam.bookapp.viewmodel.MainViewModel

class ViewModelFactory constructor(private val application: Application, private val repository: DataRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.application,this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

    /**Comments
     * ViewModelFactory class returns a new instance of the
     * CrudViewModel class.
     *
     *
     * Q. why we use ViewModelFactory class?
     *
     * Ans: We can not create ViewModel on our own. We need ViewModelProviders utility
     * provided by Android to create ViewModels.
     * But ViewModelProviders can only instantiate ViewModels with no arg constructor.
     * So if I have a ViewModel with multiple arguments, then I need to use a Factory
     * that I can pass to ViewModelProviders to use when an instance of MyViewModel is required.
     */

}