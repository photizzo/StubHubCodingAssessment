package com.example.stubhubpractise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stubhubpractise.databinding.RecyclerListItemBinding
import com.example.stubhubpractise.model.Event

class EventRecyclerAdapter(
    var eventList: List<Event>
) : RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecyclerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    inner class ViewHolder(val binding: RecyclerListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(eventData: Event) {
            binding.titleTextView.text = eventData.name
            binding.venueTextView.text = "Venue: ${eventData.city.toString()}"
            binding.priceTextView.text = "Price: $ ${eventData.price.toString()}"
            binding.dateTextView.text = "Date: ${eventData.city.toString()}"
        }
    }

}