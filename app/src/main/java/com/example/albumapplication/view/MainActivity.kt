package com.example.albumapplication.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.albumapplication.domain.utils.Constants
import com.example.albumapplication.databinding.ActivityMainBinding
import com.example.albumapplication.di.DaggerAppComponent
import com.example.albumapplication.viewModel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModel: MainViewModel
    private val TAG: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAppComponent.factory().create(applicationContext).inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getAllAlbums()
    }
}