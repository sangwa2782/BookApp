package com.ajayam.bookapp.model

data class Index(
    val authorid: Int,
    val cd_downloads: Int,
    val curriculum_tags: List<String>,
    val downloadid: Int,
    val educator: String,
    val id: Int,
    val owned: Int,
    val progress_tracking: Double,
    val purchase_order: Int,
    val release_date: String,
    val sale: Int,
    val series_tags: List<String>,
    val skill_tags: List<String>,
    val status: Int,
    val style_tags: List<String>,
    val title: String,
    val video_count: Int,
    val watched: Int
)