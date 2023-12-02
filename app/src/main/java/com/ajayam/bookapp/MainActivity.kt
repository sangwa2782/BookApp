package com.ajayam.bookapp

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ajayam.bookapp.data.ApiService
import com.ajayam.bookapp.data.DataRepository
import com.ajayam.bookapp.databinding.ActivityMainBinding
import com.ajayam.bookapp.viewmodel.MainViewModel
import com.ajayam.mygit.util.ViewModelFactory
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()

        //get data
        getCourses()




    }

    private fun getCourses() {

        //observing view model live data
        viewModel.courseData.observe(this) {
            if (it != null) {
                Toast.makeText(this, "Data fetched successfully", Toast.LENGTH_SHORT).show()
                Log.e("TAG", "getRepoData: " + Gson().toJson(it))
//                setAdapter(it)

            }

            //observing errors
            viewModel.errorMessage.observe(this) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                Log.e("TAG", "errorMessage: " + Gson().toJson(it))
            }

            //observing process
            viewModel.loading.observe(this, Observer {
                if (it) {
                    //show loader
                    Log.e("TAG", "loading: $it")
                } else {
                    //hide loader
                    Log.e("TAG", "loading: $it")
                }

            })

        }

        viewModel.getCoursesData()
    }

    private fun initialize() {
        //initialise instances
        binding.rlSmart.setOnClickListener(this)
        binding.rlUser.setOnClickListener(this)
        binding.rlCurated.setOnClickListener(this)

        val retrofitService = ApiService.getInstance()
        val dataRepository = DataRepository(retrofitService)
        viewModel = ViewModelProvider(this, ViewModelFactory(Application(), dataRepository)).get(
            MainViewModel::class.java
        )
    }

    override fun onClick(p0: View?) {

    }
}