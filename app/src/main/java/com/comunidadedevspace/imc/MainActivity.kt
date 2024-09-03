package com.comunidadedevspace.imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private companion object {
        const val ONE_DECIMAL = "#.0"
        const val IMC_VALUE = "imc_value"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textWeight = findViewById<TextInputEditText>(R.id.textInputEditWight)
        val textHeight = findViewById<TextInputEditText>(R.id.textInputEditHeight)
        val calculateButton = findViewById<Button>(R.id.buttonCalculate)

        calculateButton.setOnClickListener {
            validateFields(textWeight, textHeight)
        }
    }

    private fun validateFields(weight: TextInputEditText, height: TextInputEditText) {

        if (weight.text.isNullOrEmpty() && height.text.isNullOrEmpty()) {
            Toast.makeText(this, getString(R.string.alert_all_fields_empty), Toast.LENGTH_SHORT).show()
        } else if (weight.text.isNullOrEmpty()) {
            Toast.makeText(this, getString(R.string.alert_weight_empty), Toast.LENGTH_SHORT).show()
            weight.requestFocus()
        } else if (height.text.isNullOrEmpty()) {
            Toast.makeText(this, getString(R.string.alert_height_empty), Toast.LENGTH_SHORT).show()
            height.requestFocus()
        } else {
            val imcResult = calculateImc(
                weight = weight.text.toString().toFloat(),
                height = height.text.toString().toFloat()
            )

            Intent(this, ResultActivity::class.java).apply {
                putExtra(IMC_VALUE, imcResult)
                startActivity(this)
            }
        }
    }

    private fun calculateImc(weight: Float, height: Float): String {
        val result = DecimalFormat(ONE_DECIMAL)
        val imc = weight / (height * height)
        return result.format(imc)
    }
}