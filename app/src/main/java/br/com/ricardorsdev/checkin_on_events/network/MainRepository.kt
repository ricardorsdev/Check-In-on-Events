package br.com.ricardorsdev.checkin_on_events.network

import br.com.ricardorsdev.checkin_on_events.api.IApi
import br.com.ricardorsdev.checkin_on_events.models.Event
import br.com.ricardorsdev.checkin_on_events.models.Subscription
import br.com.ricardorsdev.checkin_on_events.models.SubscriptionResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

class MainRepository @Inject constructor(
	private val api: IApi
) : IMainRepository {

	override suspend fun getEvents() = flow {
		val req = api.getEventsAsync()

		try {
			val res = req.await()

			when (res.code()) {
				HttpsURLConnection.HTTP_OK -> res.body()?.let {
					emit(Result.success(it))
				}

				else -> emit(Result.failure<List<Event>>(Exception()))
			}

		} catch (t: Throwable) {
			t.printStackTrace()
			emit(Result.failure<List<Event>>(Exception()))
		}
	}

	override suspend fun getEventDetails(eventId: String) = flow {
		val req = api.getEventDetailsAsync(eventId)

		try {
			val res = req.await()

			when (res.code()) {
				HttpsURLConnection.HTTP_OK -> res.body()?.let {
					emit(Result.success(it))
				}

				else -> emit(Result.failure<Event>(Exception()))
			}

		} catch (t: Throwable) {
			t.printStackTrace()
			emit(Result.failure<Event>(Exception()))
		}
	}

	override suspend fun postSubscription(subscription: Subscription) = flow {
		val req = api.postSubscriptionAsync(subscription)

		try {
			val res = req.await()

			when (res.code()) {
				HttpsURLConnection.HTTP_CREATED -> res.body()?.let {
					emit(Result.success(it))
				}

				else -> emit(Result.failure<SubscriptionResponse>(Exception()))
			}

		} catch (t: Throwable) {
			t.printStackTrace()
			emit(Result.failure<SubscriptionResponse>(Exception()))
		}
	}
}