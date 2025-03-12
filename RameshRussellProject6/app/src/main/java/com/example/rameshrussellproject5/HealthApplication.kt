package com.example.rameshrussellproject5

import android.app.Application

class HealthApplication: Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}