package com.example.kattabozor.util

import android.util.DisplayMetrics
import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun Int.dpToPx(displayMetrics: DisplayMetrics?): Int {
    return (this * displayMetrics!!.density).toInt()
}

