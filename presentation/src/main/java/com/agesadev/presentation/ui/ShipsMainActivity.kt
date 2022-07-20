package com.agesadev.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.agesadev.presentation.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShipsMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ships_main)
    }
}