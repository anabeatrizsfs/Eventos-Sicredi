package com.beatriz.eventos.ui.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.beatriz.eventos.R
import com.beatriz.eventos.databinding.ActivityEventDetailBinding

class EventDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityEventDetailBinding.inflate(layoutInflater).apply {
            setContentView(root)
            setSupportActionBar(toolbar)
            supportActionBar?.run {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }
    }
}