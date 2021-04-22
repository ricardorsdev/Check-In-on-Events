package br.com.ricardorsdev.checkin_on_events.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Subscription (
	var eventId: String,
	var name: String,
	var email: String
) : Parcelable