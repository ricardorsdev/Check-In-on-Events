package br.com.ricardorsdev.checkin_on_events.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
	fun timestampToStringDate(timestamp: Long): String {
		return try {
			val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
			val date = Date(timestamp * 1000)
			sdf.format(date)
		} catch (e: Exception) {
			e.printStackTrace()
			return ""
		}
	}

	fun getDateFromDateTime(dateTime: String): String {
		return dateTime.substringBefore(" ")
	}

	fun getTimeFromDateTime(dateTime: String): String {
		return dateTime.substringAfter(" ")
	}
}