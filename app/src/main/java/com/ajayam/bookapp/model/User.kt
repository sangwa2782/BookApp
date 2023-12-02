package com.ajayam.bookapp.model

data class User(
    val courses: List<Int>,
    val description: String,
    val id: Int,
    val is_archive: Int,
    val is_default: Int,
    val label: String
)