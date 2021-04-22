package br.com.ricardorsdev.checkin_on_events.ui.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.ricardorsdev.checkin_on_events.R
import br.com.ricardorsdev.checkin_on_events.databinding.ItemEventListBinding
import br.com.ricardorsdev.checkin_on_events.models.Event
import br.com.ricardorsdev.checkin_on_events.utils.DateUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class EventListAdapter(
	val context: Context,
	private val eventList: MutableList<Event>,
	private val listener: EventListListener
) : RecyclerView.Adapter<EventListAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val binding: ItemEventListBinding = DataBindingUtil.inflate(
			LayoutInflater.from(context),
			R.layout.item_event_list,
			parent,
			false
		)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		eventList.sortedBy { it.date }
		holder.bind(eventList[position], context, listener)
	}

	override fun getItemCount() = eventList.size

	class ViewHolder(
		private val binding: ItemEventListBinding
	) : RecyclerView.ViewHolder(binding.root) {

		fun bind(event: Event, context: Context, listener: EventListListener) {
			binding.title = event.title

			event.date?.let { timestamp ->
				binding.date = DateUtils.timestampToStringDate(timestamp, "dd/MM/yyyy HH:mm:ss")
			}

			event.image?.let { url ->
				Glide.with(context)
					.load(url)
					.centerCrop()
					.error(R.drawable.ic_broken_image)
					.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
					.into(binding.ivEventImage)
			}

			binding.cvEvent.setOnClickListener {
				listener.onCardViewClicked(event.id)
			}
		}

	}
}