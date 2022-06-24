package com.beatriz.eventos.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.beatriz.eventos.R
import com.bumptech.glide.Glide
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("app:visibility")
fun View.setVisibility(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("app:formatDate")
fun TextView.formatDate(timestamp: Long) {
    text = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(timestamp))
}

@BindingAdapter("app:formatMoney")
fun TextView.formatMoney(number: Float) {
    text = """R$ """ + DecimalFormat("#,###").format(number)
}

@BindingAdapter("image")
fun setImage(image: ImageView, url: String?) {
    Glide.with(image.context).load(url).centerCrop()
        .placeholder(R.drawable.placeholder)
        .into(image)
}
