package br.com.ricardorsdev.checkin_on_events.ui.views.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.ricardorsdev.checkin_on_events.R
import br.com.ricardorsdev.checkin_on_events.databinding.FragmentEventDetailBinding
import br.com.ricardorsdev.checkin_on_events.models.Event
import br.com.ricardorsdev.checkin_on_events.ui.viewModels.EventDetailViewModel
import br.com.ricardorsdev.checkin_on_events.utils.DateUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint
import java.lang.String.format
import java.util.*


@AndroidEntryPoint
class EventDetailFragment : Fragment() {

	private lateinit var binding: FragmentEventDetailBinding
	private val viewModel: EventDetailViewModel by viewModels()
	private val args: EventDetailFragmentArgs by navArgs()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		super.onCreateView(inflater, container, savedInstanceState)
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_detail, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val eventId = args.eventId

		setObservers()
		viewModel.getEventDetails(eventId)
	}

	private fun setObservers() {
		viewModel.eventDetailsObservable.observe(viewLifecycleOwner, {
			updateView(it)
		})
	}

	private fun updateView(event: Event) {
		event.image?.let { url ->
			Glide.with(requireContext())
				.load(url)
				.centerCrop()
				.error(R.drawable.ic_broken_image)
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				.into(binding.ivEventImage)
		}

		binding.title = event.title

		val dateTime = DateUtils.timestampToStringDate(event.date.toLong())
		val date = DateUtils.getDateFromDateTime(dateTime)
		val time = DateUtils.getTimeFromDateTime(dateTime)

		binding.date = "Data: $date"
		binding.time = "Horário: $time"

		binding.description = event.description

		binding.price = "Valor: R\$${event.price}"

		binding.btnMaps.setOnClickListener {
			val uri = format(Locale.getDefault(), "geo:%f,%f", event.latitude, event.longitude)
			val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
			requireContext().startActivity(intent)
		}

		binding.btnSubscription.setOnClickListener {
			val action = EventDetailFragmentDirections.actionEventDetailFragmentToSubscriptionFragment(
				event.id,
				event.title
			)
			findNavController().navigate(action)
		}
	}
}
