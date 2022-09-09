package com.example.firsthomework.services.entities

data class NewsRemote(val text: String)

fun NewsRemote.toDomain() = News(this.text)