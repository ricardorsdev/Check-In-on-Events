package br.com.ricardorsdev.checkin_on_events.models

import android.os.Parcelable
import com.google.gson.JsonArray
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Event(
	var people: String,
	var date: String,
	var description: String,
	var image: String,
	var longitude: Float,
	var latitude: Float,
	var price: Double,
	var title: String,
	var id: String
) : Parcelable