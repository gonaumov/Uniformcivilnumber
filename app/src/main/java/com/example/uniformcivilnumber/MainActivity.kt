package com.example.uniformcivilnumber
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.uniformcivilnumber.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.gotoGeneratePage.setOnClickListener {
            goToToGeneratePage(it)
        }
        binding.gotoParsePage.setOnClickListener {
            goToToParsePage(it)
        }
    }

    fun goToToParsePage(view: View) {
        val intent = Intent(this, ParseActivity::class.java)
        startActivity(intent)
    }

    fun goToToGeneratePage(view: View) {
        val intent = Intent(this, GenerateActivity::class.java)
        startActivity(intent)
    }

}
