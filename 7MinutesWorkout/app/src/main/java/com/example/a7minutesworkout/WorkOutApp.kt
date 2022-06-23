package com.example.a7minutesworkout

import android.app.Application

class WorkOutApp : Application() {
    val db by lazy {
        HistoryDataBase.getInstance(this)
    }
}