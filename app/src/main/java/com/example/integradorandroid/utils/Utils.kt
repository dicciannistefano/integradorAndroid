package com.example.integradorandroid.utils

import android.widget.Button
import com.example.integradorandroid.R

fun Button.disableButton(){
    this.setBackgroundResource(R.drawable.shape_disable)
    this.isClickable = false
}

fun Button.enableButton(){
    this.setBackgroundResource(R.drawable.shape_enable)
    this.isClickable = true
}