package br.com.ricardorsdev.network

import br.com.ricardorsdev.checkin_on_events.models.Event
import kotlinx.coroutines.flow.Flow

interface IMainRepository {
	suspend fun getEvents(): Flow<Result<List<Event>>>
}