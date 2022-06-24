package com.beatriz.eventos.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:visibility")
fun View.setVisibility(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
