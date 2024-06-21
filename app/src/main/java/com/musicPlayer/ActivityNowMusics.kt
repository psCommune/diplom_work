package com.musicPlayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.musicPlayer.databinding.ActivityNowMusicsBinding

class ActivityNowMusics : AppCompatActivity() {

    private lateinit var binding: ActivityNowMusicsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNowMusicsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}