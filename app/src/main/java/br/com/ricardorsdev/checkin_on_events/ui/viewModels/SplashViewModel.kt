package br.com.ricardorsdev.checkin_on_events.ui.viewModels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ricardorsdev.checkin_on_events.models.Event
import br.com.ricardorsdev.network.IMainRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel @ViewModelInject constructor(
	private val repository: IMainRepository
): ViewModel() {

	val eventsObservable = MutableLiveData<List<Event>>()

	fun getEvents() {
		viewModelScope.launch {
			repository.getEvents().collect { result ->
				result.onSuccess {
					eventsObservable.postValue(it)
				}
				result.onFailure {

				}
			}
		}
	}
}