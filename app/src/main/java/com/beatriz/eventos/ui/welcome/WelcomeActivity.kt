package com.beatriz.eventos.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.beatriz.eventos.R
import com.beatriz.eventos.databinding.ActivityWelcomeBinding
import com.beatriz.eventos.ui.event.EventDetailActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityWelcomeBinding.inflate(layoutInflater).apply {
            setContentView(root)

            btnStart.setOnClickListener {
                startActivity(Intent(this@WelcomeActivity, EventDetailActivity::class.java))
                finish()
            }
        }
    }
}
