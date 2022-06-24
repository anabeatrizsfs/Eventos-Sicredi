package com.beatriz.eventos.ui.event

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.beatriz.eventos.R
import com.beatriz.eventos.data.constants.EventConstants.EVENT_ID
import com.beatriz.eventos.databinding.ActivityEventDetailBinding
import com.beatriz.eventos.utils.ResourceStatus
import com.beatriz.eventos.utils.showToast
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
                eventViewModel.checkIn()
            }
            boxCheckin.setOnClickListener { shareEvent() }
        }

        with(eventViewModel) {
            getEvent(intent.getIntExtra(EVENT_ID, 0)).observe(this@EventDetailActivity) {
                setEventLoading(false)
                when (it.status) {
                    ResourceStatus.SUCCESS -> binding.event = it.data
                    ResourceStatus.LOADING -> setEventLoading(true)
                    ResourceStatus.ERROR -> setError()
                }
            }

            checkinLiveData.observe(this@EventDetailActivity) {
                setCheckinLoading(false)
                when (it.status) {
                    ResourceStatus.SUCCESS -> showToast(getString(R.string.success_checkin))
                    ResourceStatus.LOADING -> setCheckinLoading(true)
                    ResourceStatus.ERROR -> showToast(getString(R.string.error_checkin))
                }
            }
        }
    }

    private fun setCheckinLoading(enabled: Boolean) {
        binding.run {
            if (enabled) {
                loading.visibility = View.VISIBLE
                btnCheckin.visibility = View.GONE
            } else {
                loading.visibility = View.GONE
                btnCheckin.visibility = View.VISIBLE
            }
        }
    }

    private fun setEventLoading(enabled: Boolean) {
        if (enabled) {
            skeleton.showSkeleton()
        } else {
            skeleton.showOriginal()
        }
    }

    private fun setError() {
        binding.run {
            boxError.visibility = View.VISIBLE
            eventsScroll.visibility = View.INVISIBLE
            boxCheckin.visibility = View.INVISIBLE
        }
    }

    private fun shareEvent() {
        Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, binding.eventTitle.text);
            startActivity(Intent.createChooser(this, getString(R.string.title_share)))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
