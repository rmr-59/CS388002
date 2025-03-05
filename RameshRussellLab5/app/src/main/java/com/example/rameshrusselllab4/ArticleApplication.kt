package com.example.rameshrusselllab4

import android.app.Application

class ArticleApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}