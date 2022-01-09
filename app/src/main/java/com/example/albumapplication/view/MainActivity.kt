package com.example.albumapplication.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.albumapplication.domain.utils.Constants
import com.example.albumapplication.adapter.RecyclerViewAdapter
import com.example.albumapplication.databinding.ActivityMainBinding
import com.example.albumapplication.di.DaggerAppComponent
import com.example.albumapplication.viewModel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var mAdapter: RecyclerViewAdapter
    private val TAG: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAppComponent.factory().create(applicationContext).inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Layout manager
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.albumList.observe(this,{
            it?.let {
                mAdapter = RecyclerViewAdapter(it)
                binding.recyclerView.adapter = mAdapter
                setVisibilities(View.INVISIBLE, View.VISIBLE)
            }?: run{
                setVisibilities(View.VISIBLE, View.INVISIBLE)
            }
        })

        viewModel.getAllAlbums()
    }

    /**
     * set view's visibility
     */
    fun setVisibilities(textVisibility: Int, recyclerVisibility: Int){
        binding.recyclerView.visibility = recyclerVisibility
        binding.textView.visibility = textVisibility
    }
}