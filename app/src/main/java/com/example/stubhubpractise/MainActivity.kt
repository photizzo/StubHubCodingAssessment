package com.example.stubhubpractise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stubhubpractise.databinding.ActivityMainBinding
import com.example.stubhubpractise.model.EventData

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        viewModel.getEventsData(applicationContext)

        viewModel.eventData.observe(this, Observer {
            updateList(it)
        })
    }

    private fun setupUI() {
        binding.filterButton.setOnClickListener {
            viewModel.searchEvent(
                binding.cityEditText.text.toString(),
                binding.priceEditText.text.toString()
            )
        }

        val divider = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        val adapter = EventRecyclerAdapter(listOf())
        val layoutManager = LinearLayoutManager(this)
        binding.eventList.layoutManager = layoutManager
        binding.eventList.addItemDecoration(divider)
        binding.eventList.adapter = adapter

    }

    private fun updateList(eventData: EventData) {
        (binding.eventList.adapter as EventRecyclerAdapter).eventList = eventData.children
        binding.eventList.adapter?.notifyDataSetChanged()
    }
}