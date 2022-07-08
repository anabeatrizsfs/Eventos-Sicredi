package com.beatriz.eventos.utils

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.faltenreich.skeletonlayout.Skeleton

fun Context.showToast(message: CharSequence) =
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_SHORT
    ).show()

fun String.isValidEmail() =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun Skeleton.setEventsLoading(enabled: Boolean) {
    if (enabled) {
        showSkeleton()
    } else {
        showOriginal()
    }
}

fun Context.shareEvent(event: CharSequence, title: String) {
    Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, event);
        startActivity(Intent.createChooser(this, title))
    }
}