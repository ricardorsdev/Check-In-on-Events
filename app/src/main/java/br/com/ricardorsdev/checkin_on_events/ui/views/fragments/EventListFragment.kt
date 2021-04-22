package br.com.ricardorsdev.checkin_on_events.ui.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import br.com.ricardorsdev.checkin_on_events.R
import br.com.ricardorsdev.checkin_on_events.databinding.FragmentEventListBinding
import br.com.ricardorsdev.checkin_on_events.models.Event
import br.com.ricardorsdev.checkin_on_events.ui.viewModels.EventListViewModel
import br.com.ricardorsdev.checkin_on_events.ui.views.adapters.EventListAdapter
import br.com.ricardorsdev.checkin_on_events.ui.views.adapters.EventListListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventListFragment : Fragment() {

	private lateinit var binding: FragmentEventListBinding
	private val viewModel: EventListViewModel by viewModels()

	private var adapter: EventListAdapter? = null
	private var eventList: MutableList<Event> = mutableListOf()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		super.onCreateView(inflater, container, savedInstanceState)
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_list, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initView()
		setObservers()
		viewModel.getEvents()
	}

	private fun setObservers() {
		viewModel.eventsObservable.observe(viewLifecycleOwner, {
			this.eventList.clear()
			this.eventList.addAll(it)
			adapter?.notifyDataSetChanged()
		})
	}

	private fun initView() {
		adapter = EventListAdapter(
			requireContext(),
			eventList,
			object : EventListListener {
				override fun onCardViewClicked(id: String) {
					val action = EventListFragmentDirections.actionEventListFragmentToEventDetailFragment(id)
					findNavController().navigate(action)
				}
			})
		binding.rvEventsList.adapter = adapter
		binding.rvEventsList.layoutManager = GridLayoutManager(requireContext(), COLUMNS)
	}

	companion object {
		const val COLUMNS = 2
	}
}