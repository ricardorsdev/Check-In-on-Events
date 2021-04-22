package br.com.ricardorsdev.checkin_on_events.ui.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ricardorsdev.checkin_on_events.models.Event
import br.com.ricardorsdev.checkin_on_events.models.Subscription
import br.com.ricardorsdev.checkin_on_events.models.SubscriptionResponse
import br.com.ricardorsdev.checkin_on_events.network.IMainRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SubscriptionViewModel @ViewModelInject constructor(
	private val repository: IMainRepository
): ViewModel() {

	val subscriptionObservable = MutableLiveData<SubscriptionResponse>()

	fun submit(subscription: Subscription) {
		viewModelScope.launch {
			repository.postSubscription(subscription).collect { result ->
				result.onSuccess {
					subscriptionObservable.postValue(it)
				}
				result.onFailure {
					it.printStackTrace()
				}
			}
		}
	}
}