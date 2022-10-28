package com.greedy.hinote

data class Books(
    val copyright: String,
    val imageUrl: String,
    val item: List<Book>,
    val itemsPerPage: Int,
    val language: String,
    val link: String,
    val maxResults: Int,
    val pubDate: String,
    val queryType: String,
    val returnCode: String,
    val returnMessage: String,
    val searchCategoryId: String,
    val searchCategoryName: String,
    val startIndex: Int,
    val title: String,
    val totalResults: Int
)