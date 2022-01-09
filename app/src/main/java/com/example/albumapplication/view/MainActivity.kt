package com.example.albumapplication.view

import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.albumapplication.domain.utils.ConnectivityReceiver
import com.albumapplication.domain.utils.Constants
import com.example.albumapplication.R
import com.example.albumapplication.adapter.RecyclerViewAdapter
import com.example.albumapplication.databinding.ActivityMainBinding
import com.example.albumapplication.di.DaggerAppComponent
import com.example.albumapplication.viewModel.MainViewModel
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var mAdapter: RecyclerViewAdapter
    private var snackBar: Snackbar? = null
    private val TAG: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAppComponent.factory().create(applicationContext).inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //register receiver for internet connectivity
        registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        setVisibilities(View.INVISIBLE, View.INVISIBLE,View.VISIBLE)

        //Layout manager
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.albumList.observe(this,{
            it?.let {
                mAdapter = RecyclerViewAdapter(it)
                binding.recyclerView.adapter = mAdapter
                setVisibilities(View.INVISIBLE, View.VISIBLE, View.INVISIBLE)
            }?: run{
                binding.errorTextView.text = getString(R.string.no_items)
                setVisibilities(View.VISIBLE, View.INVISIBLE, View.INVISIBLE)
            }
        })

        viewModel.errorMessage.observe(this,{
            binding.errorTextView.text = getString(R.string.error)
            setVisibilities(View.VISIBLE, View.INVISIBLE, View.INVISIBLE)
        })

        viewModel.getAllAlbums()
    }

    /**
     * set view's visibility
     */
    fun setVisibilities(textVisibility: Int, recyclerVisibility: Int, progressVisibility: Int){
        binding.recyclerView.visibility = recyclerVisibility
        binding.errorTextView.visibility = textVisibility
        binding.searchProgressBar.visibility = progressVisibility
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    private fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {
            snackBar = Snackbar.make(findViewById(R.id.rootLayout), getString(R.string.offline), Snackbar.LENGTH_LONG)
            snackBar?.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
            snackBar?.setBackgroundTint(Color.RED)
            snackBar?.show()
        } else {
            snackBar?.dismiss()
        }
    }
}