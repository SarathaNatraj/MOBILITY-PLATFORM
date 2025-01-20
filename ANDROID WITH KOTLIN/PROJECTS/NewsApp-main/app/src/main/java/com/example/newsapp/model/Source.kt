package com.example.newsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(val id: String,
                  val name: String,
                  val description: String,
                  val url: String
):Parcelable

