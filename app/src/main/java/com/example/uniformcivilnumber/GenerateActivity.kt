package com.example.uniformcivilnumber

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.uniformcivilnumber.databinding.ActivityGenerateBinding

class GenerateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGenerateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenerateBinding.inflate(layoutInflater)
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