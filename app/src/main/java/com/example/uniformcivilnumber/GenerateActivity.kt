package com.example.uniformcivilnumber

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.uniformcivilnumber.databinding.ActivityGenerateBinding

class GenerateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGenerateBinding
    private val unc: UniformCivilNumber = UniformCivilNumber()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenerateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            backButtonTap(it)
        }
        binding.buttonGenerateUcns.setOnClickListener {
            generateUcns()
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, unc.regions.keys.toList())
        binding.selectRegion.adapter = adapter
        binding.editMontDay.setOnKeyListener { v, keyCode, _ ->
            handleKeyEvent(v, keyCode)
        }
        binding.editMontNumber.setOnKeyListener { v, keyCode, _ ->
            handleKeyEvent(v, keyCode)
        }
        binding.editYearNumber.setOnKeyListener { v, keyCode, _ ->
            handleKeyEvent(v, keyCode)
        }
        binding.numberOfUcns.setOnKeyListener { v, keyCode, _ ->
            handleKeyEvent(v, keyCode)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun generateUcns() {
        val selectedRegion = unc.regions[binding.selectRegion.selectedItem]
        var regionId = unc.firstNum.filterValues { it == selectedRegion }.keys.first()
        val numberOfUcns = binding.numberOfUcns.text.toString().toIntOrNull() ?: 3
        val day = binding.editMontDay.text.toString().toIntOrNull() ?: 0
        val month = binding.editMontNumber.text.toString().toIntOrNull() ?: 0
        val year = binding.editYearNumber.text.toString().toIntOrNull() ?: 0
        val sex = when (binding.genderOptions.checkedRadioButtonId) {
            R.id.option_female -> 2
            R.id.option_male -> 1
            else -> 0
        }
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("mode", "generate")
        intent.putExtra("regionId", regionId)
        intent.putExtra("numberOfUcns", numberOfUcns)
        intent.putExtra("day", day)
        intent.putExtra("month", month)
        intent.putExtra("year", year)
        intent.putExtra("sex", sex)
        startActivity(intent)
    }

    fun backButtonTap(view: View) {
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