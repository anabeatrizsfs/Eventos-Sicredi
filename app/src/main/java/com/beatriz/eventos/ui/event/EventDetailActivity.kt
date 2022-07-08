package com.beatriz.eventos.ui.event

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.beatriz.eventos.R
import com.beatriz.eventos.data.constants.EventConstants.EVENT_ID
import com.beatriz.eventos.databinding.ActivityEventDetailBinding
import com.beatriz.eventos.utils.setEventsLoading
import com.beatriz.eventos.utils.shareEvent
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

            boxCheckin.setOnClickListener {
                shareEvent(binding.eventTitle.text, getString(R.string.title_share))
            }
        }
        binding.lifecycleOwner = this

        with(eventViewModel) {
            getEvent(intent.getIntExtra(EVENT_ID, 0))

            loadingEvent.observe(this@EventDetailActivity) {
                skeleton.setEventsLoading(it)
            }

            dataEvent.observe(this@EventDetailActivity) {
                binding.event = it
            }

            checkinLiveData.observe(this@EventDetailActivity) {
                showToast(getString(R.string.success_checkin))
            }

            errorCheckin.observe(this@EventDetailActivity) {
                showToast(getString(R.string.error_checkin))
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
