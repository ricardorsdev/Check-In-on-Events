package br.com.ricardorsdev.checkin_on_events.network

import br.com.ricardorsdev.checkin_on_events.models.Event
import kotlinx.coroutines.flow.Flow

interface IMainRepository {
	suspend fun getEvents(): Flow<Result<List<Event>>>
}