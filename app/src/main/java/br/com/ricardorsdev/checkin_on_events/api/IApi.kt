package br.com.ricardorsdev.checkin_on_events.api

import br.com.ricardorsdev.checkin_on_events.models.Event
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface IApi {

	@GET("/api/events")
	fun getEventsAsync(): Deferred<Response<List<Event>>>

}