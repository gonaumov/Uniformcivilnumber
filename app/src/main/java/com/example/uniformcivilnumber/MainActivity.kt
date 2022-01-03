package com.example.uniformcivilnumber
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uniformcivilnumber.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonInfo.setOnClickListener {
            displayUcnInfo()
        }
    }

    private fun displayUcnInfo() {
        val unc = UniformCivilNumber()
        binding.ucnInfoText.text = unc.info(binding.ucnNumber.text.toString())
    }
}