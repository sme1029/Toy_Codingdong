package com.greedy.hinote

import android.content.ClipData

data class Comment(
    val body: String,
    val id: Int,
    val postId: Int,
    val itemId: ClipData.Item,
    val username: CharSequence?
)

