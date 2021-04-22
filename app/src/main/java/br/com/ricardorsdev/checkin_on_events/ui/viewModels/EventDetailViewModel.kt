package br.com.ricardorsdev.checkin_on_events.ui.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ricardorsdev.checkin_on_events.models.Event
import br.com.ricardorsdev.checkin_on_events.network.IMainRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class EventDetailViewModel @ViewModelInject constructor(
	private val repository: IMainRepository
): ViewModel() {

	val eventDetailsObservable = MutableLiveData<Event>()

	fun getEventDetails(eventId: String) {
		viewModelScope.launch {
			repository.getEventDetails(eventId).collect { result ->
				result.onSuccess {
					eventDetailsObservable.postValue(it)
				}
				result.onFailure {
					it.printStackTrace()
				}
			}
		}
	}
}