package br.com.ricardorsdev.checkin_on_events.ui.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.com.ricardorsdev.checkin_on_events.R
import br.com.ricardorsdev.checkin_on_events.databinding.FragmentEventDetailBinding
import br.com.ricardorsdev.checkin_on_events.databinding.FragmentSubscriptionBinding
import br.com.ricardorsdev.checkin_on_events.models.Subscription
import br.com.ricardorsdev.checkin_on_events.ui.viewModels.EventDetailViewModel
import br.com.ricardorsdev.checkin_on_events.ui.viewModels.SubscriptionViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.content.DialogInterface
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubscriptionFragment : Fragment() {

	private lateinit var binding: FragmentSubscriptionBinding
	private val viewModel: SubscriptionViewModel by viewModels()
	private val args: SubscriptionFragmentArgs by navArgs()

	private var eventId = ""

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		super.onCreateView(inflater, container, savedInstanceState)
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_subscription, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setObservers()

		eventId = args.eventId
		binding.title = args.eventTitle
		binding.btnSubmit.setOnClickListener {
			submit()
		}
	}

	private fun submit() {
		val name = binding.etName.text.toString()
		val email = binding.etEmail.text.toString()

		val subscription = Subscription(eventId, name, email)

		viewModel.submit(subscription)
	}


	private fun setObservers() {
		viewModel.subscriptionObservable.observe(viewLifecycleOwner, {
			MaterialAlertDialogBuilder(requireContext())
				.setTitle(getString(R.string.subscription_done))
				.setMessage(getString(R.string.subcription_info))
				.setPositiveButton(getString(R.string.ok)) { dialog, _ ->
					this.finish()
					dialog.dismiss()
				}
				.show()
		})
	}

	private fun finish() {
		val action = SubscriptionFragmentDirections.actionSubscriptionFragmentToEventListFragment()
		findNavController().navigate(action)
	}
}