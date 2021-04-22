package br.com.ricardorsdev.checkin_on_events.api

import br.com.ricardorsdev.checkin_on_events.models.Event
import br.com.ricardorsdev.checkin_on_events.models.Subscription
import br.com.ricardorsdev.checkin_on_events.models.SubscriptionResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IApi {

	@GET(EVENTS_LIST)
	fun getEventsAsync(): Deferred<Response<List<Event>>>

	@GET(EVENT_DETAIL)
	fun getEventDetailsAsync(
		@Path("eventId") eventId: String
	): Deferred<Response<Event>>

	@POST(SUBSCRIPTION_EVENT)
	fun postSubscriptionAsync(@Body subscription: Subscription): Deferred<Response<SubscriptionResponse>>

	companion object {
		const val EVENTS_LIST = "/api/events"
		const val EVENT_DETAIL = "/api/events/{eventId}"
		const val SUBSCRIPTION_EVENT = "/api/checkin"
	}

}