package com.example.uniformcivilnumber

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.uniformcivilnumber.databinding.ActivityParseBinding

class ParseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParseBinding
    private val unc: UniformCivilNumber = UniformCivilNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonInfo.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("mode", "parse")
            intent.putExtra("ucn", binding.ucnNumber.text.toString())
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            backButtonTap(it)
        }
        binding.ucnNumber.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }
    }

    private fun backButtonTap(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}