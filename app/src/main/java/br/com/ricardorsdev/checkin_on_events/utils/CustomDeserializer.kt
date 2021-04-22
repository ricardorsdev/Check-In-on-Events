package br.com.ricardorsdev.checkin_on_events.utils

import br.com.ricardorsdev.checkin_on_events.models.Event
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.security.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CustomDeserializer : JsonDeserializer<ArrayList<Event>> {

	override fun deserialize(
		json: JsonElement?,
		typeOfT: Type?,
		context: JsonDeserializationContext?
	): ArrayList<Event>? {

		val eventList = ArrayList<Event>()
		val itemsJsonArray = json?.asJsonArray

		itemsJsonArray?.let { items ->
			for (item in items) {
				val jsonObject = item.asJsonObject

				val people = jsonObject.get("description").asString
				val description = jsonObject.get("description").asString
				val image = jsonObject.get("image").asString
				val longitude = jsonObject.get("longitude").asFloat
				val latitude = jsonObject.get("latitude").asFloat
				val price = jsonObject.get("price").asDouble
				val title = jsonObject.get("title").asString
				val id = jsonObject.get("id").asString

				val timeStamp = jsonObject.get("date").asLong
				val sdf = SimpleDateFormat("dd/MM/yy hh:mm a", Locale.ROOT)
				val netDate = Date(timeStamp)
				val date = sdf.format(netDate)

				eventList.add(
					Event(
						people = people,
						description = description,
						image = image,
						longitude = longitude,
						latitude = latitude,
						price = price,
						title = title,
						id = id,
						date = date
					)
				)

			}
			return eventList
		} ?: run {
			return null
		}
	}
}