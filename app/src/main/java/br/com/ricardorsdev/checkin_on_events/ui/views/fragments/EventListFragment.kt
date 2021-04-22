package br.com.ricardorsdev.checkin_on_events.ui.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import br.com.ricardorsdev.checkin_on_events.R
import br.com.ricardorsdev.checkin_on_events.databinding.FragmentEventListBinding
import br.com.ricardorsdev.checkin_on_events.ui.viewModels.MainViewModel
import br.com.ricardorsdev.checkin_on_events.ui.views.activities.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventListFragment : Fragment() {

	private lateinit var binding: FragmentEventListBinding
	private val viewModel: MainViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_list, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setObservers()
		viewModel.getEvents()
	}

	private fun setObservers() {
		viewModel.eventsObservable.observe(viewLifecycleOwner, {

		})
	}
}