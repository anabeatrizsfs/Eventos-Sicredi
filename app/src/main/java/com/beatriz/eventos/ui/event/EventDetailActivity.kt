package com.beatriz.eventos.ui.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.beatriz.eventos.data.constants.EventConstants.EVENT_ID
import com.beatriz.eventos.databinding.ActivityEventDetailBinding
import com.beatriz.eventos.utils.ResourceStatus
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.createSkeleton
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailActivity : AppCompatActivity() {

    private val eventViewModel: EventDetailViewModel by viewModel()
    private lateinit var binding: ActivityEventDetailBinding
    private lateinit var skeleton: Skeleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailBinding.inflate(layoutInflater).apply {
            setContentView(root)
            viewmodel = eventViewModel
            setSupportActionBar(toolbar)
            supportActionBar?.run {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
            skeleton = root.createSkeleton()
            btnCheckin.setOnClickListener {
                eventViewModel.checkIn(1, "Ana Beatriz", "ana@mail.com")
            }
        }

        with(eventViewModel) {
            getEvent(intent.getIntExtra(EVENT_ID, 0)).observe(this@EventDetailActivity) {
                skeleton.showOriginal()
                when (it.status) {
                    ResourceStatus.SUCCESS -> binding.event = it.data
                    ResourceStatus.LOADING -> skeleton.showSkeleton()
                    ResourceStatus.ERROR -> binding.run {
                        boxError.visibility = View.VISIBLE
                        eventsScroll.visibility = View.INVISIBLE
                        boxCheckin.visibility = View.INVISIBLE
                    }
                }
            }

            checkinLiveData.observe(this@EventDetailActivity) {
                when (it.status) {
                    ResourceStatus.SUCCESS -> Log.d("Event", "Successo! ${it.data}")
                    ResourceStatus.LOADING -> Log.d("Event", "Carregando!")
                    ResourceStatus.ERROR -> Log.d("Event", "Erro! ${it.message}")

                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
