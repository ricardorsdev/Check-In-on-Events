package br.com.ricardorsdev.checkin_on_events.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
	fun timestampToStringDate(timestamp: Long, pattern: String): String? {
		return try {
			val sdf = SimpleDateFormat(pattern, Locale.getDefault())
			val stamp = Timestamp(timestamp)
			val date = Date(stamp.time)
			sdf.format(date)
		} catch (e: Exception) {
			e.printStackTrace()
			return ""
		}
	}
}