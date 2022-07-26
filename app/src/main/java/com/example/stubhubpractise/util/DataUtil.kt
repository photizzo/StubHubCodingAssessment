package com.example.stubhubpractise.util

import android.content.Context
import com.example.stubhubpractise.model.Event
import org.json.JSONObject

object DataUtil {
    fun readJsonFileFromAssets(context: Context): String {
        var jsonString: String = ""
        jsonString = context.assets.open("data.json")
            .bufferedReader()
            .use { it.readText() }
        return jsonString
    }

    fun parseJsonDataToEvent(json: JSONObject): List<Event> {
        var result = mutableListOf<Event>()

        //Base Case
        val children = json.getJSONArray("children")
        if (children.length() == 0) {
            return listOf(getEventFromJsonData(json))
        }

        // Recursive case
        val event = getEventFromJsonData(json)
        result.add(event)
        val output = parseJsonDataToEvent(children.getJSONObject(0))
        result.addAll(output)
        return result
    }

    private fun getEventFromJsonData(json: JSONObject): Event {
        val id = json.getInt("id")
        val name = json.getString("name")
        val city = json.getString("city")
        val price = json.getString("price")
        val date = json.getString("date")
        val eventArray = json.getJSONArray("events")

        return Event(
            id,
            name,
            city,
            price.toInt(),
            date,
        )

    }
}