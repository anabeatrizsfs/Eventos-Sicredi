package com.beatriz.eventos.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.beatriz.eventos.databinding.ActivityWelcomeBinding
import com.beatriz.eventos.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeActivity : AppCompatActivity() {
    private val welcomeViewModel: WelcomeViewModel by viewModel()
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater).apply {
            setContentView(root)

            viewModel = welcomeViewModel
            btnStart.setOnClickListener {
                welcomeViewModel.setUser()
                startActivity(Intent(this@WelcomeActivity, MainActivity::class.java).apply {
                    finish()
                })
            }
        }

        welcomeViewModel.btnEnabled.observe(this) {
            binding.btnStart.isEnabled = it
        }
    }
}
