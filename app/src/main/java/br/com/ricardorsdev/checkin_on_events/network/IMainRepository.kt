package br.com.ricardorsdev.checkin_on_events.network

import br.com.ricardorsdev.checkin_on_events.models.Event
import br.com.ricardorsdev.checkin_on_events.models.Subscription
import br.com.ricardorsdev.checkin_on_events.models.SubscriptionResponse
import kotlinx.coroutines.flow.Flow

interface IMainRepository {
	suspend fun getEvents(): Flow<Result<List<Event>>>
	suspend fun getEventDetails(eventId: String): Flow<Result<Event>>
	suspend fun postSubscription(subscription: Subscription): Flow<Result<SubscriptionResponse>>
}