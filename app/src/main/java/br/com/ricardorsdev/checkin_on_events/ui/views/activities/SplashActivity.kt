package br.com.ricardorsdev.checkin_on_events.ui.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.ricardorsdev.checkin_on_events.R
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
	private val activityScope = CoroutineScope(Dispatchers.Main)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)

		activityScope.launch {
			delay(3000)

			val intent = Intent(this@SplashActivity, MainActivity::class.java)
			startActivity(intent)
			finish()
		}
	}

	override fun onPause() {
		activityScope.cancel()
		super.onPause()
	}
}