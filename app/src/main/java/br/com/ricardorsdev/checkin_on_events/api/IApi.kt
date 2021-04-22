package br.com.ricardorsdev.checkin_on_events.api

import br.com.ricardorsdev.checkin_on_events.models.Event
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IApi {

	@GET("/api/events")
	fun getEventsAsync(): Deferred<Response<List<Event>>>

	@GET("/api/events/{eventId}")
	fun getEventDetailsAsync(
		@Path("eventId") eventId: String
	): Deferred<Response<Event>>

}