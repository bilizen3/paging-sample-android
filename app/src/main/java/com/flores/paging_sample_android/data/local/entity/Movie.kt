package com.flores.paging_sample_android.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Movie(
    @PrimaryKey
    val id: Long,
    val title: String,
    val popularity: Int,
    val voteAverage: Int,
    val posterUrl: String,
    val description: String)