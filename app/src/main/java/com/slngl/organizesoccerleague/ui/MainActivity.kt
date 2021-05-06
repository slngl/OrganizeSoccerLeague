package com.slngl.organizesoccerleague.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.slngl.organizesoccerleague.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}