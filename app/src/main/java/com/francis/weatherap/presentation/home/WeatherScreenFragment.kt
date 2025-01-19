package com.francis.weatherap.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import com.francis.weatherap.R
import com.francis.weatherap.databinding.FragmentWeatherScreenBinding


class WeatherScreenFragment: Fragment(), OnQueryTextListener {

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