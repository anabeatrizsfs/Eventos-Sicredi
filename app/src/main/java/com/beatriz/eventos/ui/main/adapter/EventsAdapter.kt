package com.beatriz.eventos.ui.main.adapter

import com.beatriz.eventos.R
import com.beatriz.eventos.data.model.EventResponse
import com.beatriz.eventos.databinding.ItemEventBinding
import com.beatriz.eventos.utils.adapter.BaseAdapter

class EventsAdapter(
    list: List<EventResponse> = listOf(),
    private val eventListener: EventListener
) : BaseAdapter<ItemEventBinding, EventResponse>(list) {

    override val layoutId: Int = R.layout.item_event

    override fun bind(binding: ItemEventBinding, item: EventResponse) {
        binding.apply {
            event = item
            listener = eventListener
            executePendingBindings()
        }
    }
}

interface EventListener {
    fun onEventClicked(movie: EventResponse)
}