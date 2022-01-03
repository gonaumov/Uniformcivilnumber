package com.example.uniformcivilnumber
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.uniformcivilnumber.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val unc: UniformCivilNumber = UniformCivilNumber()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonInfo.setOnClickListener {
            displayUcnInfo()
        }
        binding.buttonGenerateUcns.setOnClickListener {
            generateUcns()
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, unc.regions.keys.toList())
        binding.selectRegion.adapter = adapter
    }

    private fun displayUcnInfo() {
        binding.ucnInfoText.text = unc.info(binding.ucnNumber.text.toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun generateUcns() {
        val selectedRegion = unc.regions[binding.selectRegion.selectedItem]
        var regionId = unc.firstNum.filterValues { it == selectedRegion }.keys.first()
        var out = ""
        val numberOfUcns = binding.numberOfUcns.text.toString().toIntOrNull() ?: 3
        val day = binding.editMontDay.text.toString().toIntOrNull() ?: 0
        val month = binding.editMontDay.text.toString().toIntOrNull() ?: 0
        val year = binding.editYearNumber.text.toString().toIntOrNull() ?: 0
        val sex = when (binding.genderOptions.checkedRadioButtonId) {
            R.id.option_female -> 2
            R.id.option_male -> 1
            else -> 0
        }

        for (i in 0 until numberOfUcns) {
            val res: String = unc?.generate(day, month, year, sex, regionId) ?: ""
            out += unc.info(res) + "\n"
        }
        binding.ucnInfoText.text = out
    }
}
