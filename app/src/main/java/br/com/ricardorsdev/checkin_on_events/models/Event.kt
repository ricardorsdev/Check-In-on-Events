package br.com.ricardorsdev.checkin_on_events.models

import android.os.Parcelable
import com.google.gson.JsonArray
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Event(
	var people: String?,
	var date: Long?,
	var description: String?,
	var image: String?,
	var longitude: Long?,
	var latitude: Long?,
	var price: Double?,
	var title: String,
	var id: String
) : Parcelable