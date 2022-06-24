package com.beatriz.eventos.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.beatriz.eventos.data.constants.EventConstants.EVENT_ID
import com.beatriz.eventos.data.model.EventResponse
import com.beatriz.eventos.databinding.ActivityMainBinding
import com.beatriz.eventos.ui.event.EventDetailActivity
import com.beatriz.eventos.ui.main.adapter.EventListener
import com.beatriz.eventos.ui.main.adapter.EventsAdapter
import com.beatriz.eventos.utils.ResourceStatus
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.createSkeleton
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), EventListener {

    private val mainViewModel: MainViewModel by viewModel()

    private val adapter by lazy {
        EventsAdapter(eventListener = this)
    }
    private lateinit var skeleton: Skeleton
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            eventsList.adapter = adapter
            skeleton = root.createSkeleton()
        }

        with(mainViewModel) {
            getAllEvents().observe(this@MainActivity) {
                setEventsLoading(false)
                when (it.status) {
                    ResourceStatus.SUCCESS -> {
                        it.data?.run {
                            adapter.updateData(this)
                        }
                    }
                    ResourceStatus.LOADING -> setEventsLoading(true)
                    ResourceStatus.ERROR -> setError()
                }
            }
        }
    }

    private fun setEventsLoading(enabled: Boolean) {
        if (enabled) {
            skeleton.showSkeleton()
        } else {
            skeleton.showOriginal()
        }
    }

    private fun setError() {
        binding.run {
            boxError.visibility = View.VISIBLE
            content.visibility = View.INVISIBLE
        }
    }

    override fun onEventClicked(event: EventResponse) {
        Intent(this, EventDetailActivity::class.java).apply {
            putExtra(EVENT_ID, event.id)
            startActivity(this)
        }
    }
}