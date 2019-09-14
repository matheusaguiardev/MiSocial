package br.com.aguiar.misocial.ui.extension

import android.view.View

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toHide() {
    this.visibility = View.GONE
}