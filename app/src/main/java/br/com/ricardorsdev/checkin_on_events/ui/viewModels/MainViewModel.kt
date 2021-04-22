package br.com.ricardorsdev.checkin_on_events.ui.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ricardorsdev.checkin_on_events.models.Event
import br.com.ricardorsdev.network.IMainRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainViewModel @ViewModelInject constructor(
	private val repository: IMainRepository
): ViewModel() {

	val eventsObservable = MutableLiveData<ArrayList<Event>>()

	fun getEvents() {
		viewModelScope.launch {
			repository.getEvents().collect { result ->
				result.onSuccess {
					eventsObservable.postValue(it as ArrayList<Event>)
				}
				result.onFailure {

				}
			}
		}
	}
}