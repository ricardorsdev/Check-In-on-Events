package br.com.ricardorsdev.checkin_on_events.models

import android.os.Parcelable
import com.google.gson.JsonArray
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Event(
	var id: String,
	var title: String,
	var date: String,
	var image: String? = null,
	var description: String? = null,
	var price: Double? = null,
	var longitude: Float? = null,
	var latitude: Float? = null,
	var people: List<String>? = null
) : Parcelable