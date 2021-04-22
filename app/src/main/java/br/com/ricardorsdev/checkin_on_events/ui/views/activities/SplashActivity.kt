package br.com.ricardorsdev.checkin_on_events.ui.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import br.com.ricardorsdev.checkin_on_events.R
import br.com.ricardorsdev.checkin_on_events.databinding.ActivitySplashBinding
import br.com.ricardorsdev.checkin_on_events.ui.viewModels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

	private lateinit var binding: ActivitySplashBinding

	private val viewModel: SplashViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

		setObservers()
		viewModel.getEvents()
	}


	private fun setObservers() {
		viewModel.eventsObservable.observe(this, {
			startActivity(Intent(this, MainActivity::class.java))
			finish()
		})
	}
}