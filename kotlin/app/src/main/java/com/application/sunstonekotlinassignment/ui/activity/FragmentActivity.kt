package com.application.sunstonekotlinassignment.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.sunstonekotlinassignment.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
    }
}