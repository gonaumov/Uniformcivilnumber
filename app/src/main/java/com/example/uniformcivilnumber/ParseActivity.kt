package com.example.uniformcivilnumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.uniformcivilnumber.databinding.ActivityParseBinding

class ParseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            backButtonTap(it)
        }
    }

    fun backButtonTap(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}