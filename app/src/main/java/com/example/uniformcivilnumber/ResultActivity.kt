package com.example.uniformcivilnumber

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.uniformcivilnumber.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var mode: ResultActivityMode = ResultActivityMode.DEFAULT
    private val unc: UniformCivilNumber = UniformCivilNumber()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mode = when (intent.extras?.getString("mode")) {
            ResultActivityMode.GENERATE.mode -> {
                val regionId = intent.extras!!.getInt("regionId")
                val numberOfUcns = intent.extras!!.getInt("numberOfUcns")
                val day = intent.extras!!.getInt("day")
                val month = intent.extras!!.getInt("month")
                val year = intent.extras!!.getInt("year")
                val sex = intent.extras!!.getInt("sex")
                var out = ""
                for (i in 0 until numberOfUcns) {
                  val res: String = unc?.generate(day, month, year, sex, regionId) ?: ""
                  out += unc.info(res) + "\n"
                }
                binding.ResultTextView.text = out
                ResultActivityMode.GENERATE
            }
            ResultActivityMode.PARSE.mode -> {
                binding.ResultTextView.text = unc.info(intent.extras?.getString("ucn") ?: "")
                ResultActivityMode.PARSE
            }
            else -> ResultActivityMode.DEFAULT
        }
        binding.backButtonResult.setOnClickListener {
            backButtonTap()
        }
    }

    private fun backButtonTap() {
        if (mode == ResultActivityMode.PARSE) {
            val intent = Intent(this, ParseActivity::class.java)
            startActivity(intent)
        } else if (mode == ResultActivityMode.GENERATE) {
            val intent = Intent(this, GenerateActivity::class.java)
            startActivity(intent)
        }
    }
}

