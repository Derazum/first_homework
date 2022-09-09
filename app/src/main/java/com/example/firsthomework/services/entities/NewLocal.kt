package com.example.firsthomework.services.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsLocal(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "newsId")
    var id: Int = 0,
    @ColumnInfo(name = "newsText")
    val text: String
    )

fun NewsLocal.toDomain() = News(this.text)