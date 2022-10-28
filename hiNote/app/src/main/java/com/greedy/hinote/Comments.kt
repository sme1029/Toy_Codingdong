package com.greedy.hinote

data class Comments(
    val comments: List<Comment>,
    val limit: Int,
    val skip: Int,
    val total: Int
)