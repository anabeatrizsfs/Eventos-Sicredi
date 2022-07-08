package com.beatriz.eventos.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.beatriz.eventos.data.constants.EventConstants.EVENT_ID
import com.beatriz.eventos.data.model.EventResponse
import com.beatriz.eventos.databinding.ActivityMainBinding
import com.beatriz.eventos.ui.event.EventDetailActivity
import com.beatriz.eventos.ui.main.adapter.EventListener
import com.beatriz.eventos.ui.main.adapter.EventsAdapter
import com.beatriz.eventos.utils.setEventsLoading
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.createSkeleton
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), EventListener {

    private val mainViewModel: MainViewModel by viewModel()
    lateinit var skeleton: Skeleton

    private val adapter by lazy {
        EventsAdapter(eventListener = this)
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            viewModel = mainViewModel
            eventsList.adapter = adapter
            skeleton = root.createSkeleton()
        }
        binding.lifecycleOwner = this

        with(mainViewModel) {
            data.observe(this@MainActivity) {
                it?.run { adapter.updateData(this) }
            }

            loading.observe(this@MainActivity) {
                skeleton.setEventsLoading(it)
            }
        }
    }

    override fun onEventClicked(event: EventResponse) {
        Intent(this, EventDetailActivity::class.java).apply {
            putExtra(EVENT_ID, event.id)
            startActivity(this)
        }
    }
}