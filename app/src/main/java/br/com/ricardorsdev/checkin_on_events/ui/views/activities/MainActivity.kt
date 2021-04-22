package br.com.ricardorsdev.checkin_on_events.ui.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.ricardorsdev.checkin_on_events.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}
}