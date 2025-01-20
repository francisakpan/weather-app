package com.francis.weatherap.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.francis.weatherap.R
import com.francis.weatherap.core.connectivity.def.ConnectivityStatus
import com.francis.weatherap.databinding.FragmentWeatherScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherScreenFragment : Fragment(), OnQueryTextListener {

    private val viewmodel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentWeatherScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        observeNetworkConnectivity()

    }

    private fun observeNetworkConnectivity() = lifecycleScope.launch {
        viewmodel.connectivityStatus.flowWithLifecycle(
            viewLifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
            .collect { status ->
                handleConnectivityStatus(status)
            }
    }


    private fun handleConnectivityStatus(status: ConnectivityStatus) {
        when (status) {
            ConnectivityStatus.Connected -> {
                Toast.makeText(context, "Connected to the Internet", Toast.LENGTH_SHORT).show()
            }

            ConnectivityStatus.Disconnected -> {
                Toast.makeText(context, "Disconnected from the Internet", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupToolbar() {
        binding.toolsBar.inflateMenu(R.menu.menu)
        val searchView = binding.toolsBar.menu.findItem(R.id.search).actionView as SearchView
        searchView.setOnQueryTextListener(this)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
//        TODO("Not yet implemented")
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d(javaClass.simpleName, "onQueryTextChange: $newText")
        return true
    }
}