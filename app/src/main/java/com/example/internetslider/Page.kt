package com.example.internetslider.model

import java.io.Serializable

data class Page(
    val title: String,
    val url: String,
    val iconResId: Int
) : Serializable
