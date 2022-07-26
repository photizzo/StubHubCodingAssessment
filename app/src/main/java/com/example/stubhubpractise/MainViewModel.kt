package com.example.stubhubpractise

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stubhubpractise.model.Event
import com.example.stubhubpractise.model.EventData
import com.example.stubhubpractise.util.DataUtil
import org.json.JSONObject

class MainViewModel : ViewModel() {

    var eventData: MutableLiveData<EventData> = MutableLiveData()
        private set
    private val eventDataList: MutableList<Event> = mutableListOf()

    fun getEventsData(context: Context) {
        val jsonString = DataUtil.readJsonFileFromAssets(context)
        val json = JSONObject(jsonString)
        val eventList = DataUtil.parseJsonDataToEvent(json)
        eventDataList.addAll(eventList)
        eventData.value = EventData(eventList)
    }

    fun searchEvent(city: String = "", price: String) {
        val filteredList = eventDataList
            .filter { event ->
                if (city.isEmpty()) {
                    true
                } else event.name.lowercase().contains(city.lowercase())
            }.filter { event ->
                if (price.isEmpty()) {
                    true
                } else {
                    event.price <= price.toInt()
                }
            }
        eventData.value = EventData(filteredList)
    }
}