package com.example.waybill.presentation.utils.extensions

import android.view.View

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE
    else View.INVISIBLE
}

fun View.goneVisible(isGone: Boolean) {
    visibility = if (isGone) View.GONE
    else View.VISIBLE
}