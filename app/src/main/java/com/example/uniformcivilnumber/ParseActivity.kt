package com.example.uniformcivilnumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.uniformcivilnumber.databinding.ActivityParseBinding

class ParseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParseBinding
    private val unc: UniformCivilNumber = UniformCivilNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonInfo.setOnClickListener {
            displayUcnInfo()
        }
        binding.button.setOnClickListener {
            backButtonTap(it)
        }
    }

    private fun backButtonTap(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun displayUcnInfo() {
        binding.ucnInfoText.text = unc.info(binding.ucnNumber.text.toString())
    }
}