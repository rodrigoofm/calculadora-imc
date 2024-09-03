package com.comunidadedevspace.imc

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
  private companion object {
    const val IMC_VALUE = "imc_value"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_result)

    val imcValue = intent.getStringExtra(IMC_VALUE)
    val imcResult = findViewById<TextView>(R.id.tvImcResult)
    imcResult.text = imcValue
    imcValue?.let {
      setClassification(it)
    }
  }

  private fun setClassification(imcValue: String) {
    val imcClassification = findViewById<TextView>(R.id.tvClassification)
    when (imcValue) {
      in "0".."16.9" -> {
        imcClassification.text = getString(R.string.label_magreza)
        imcClassification.setTextColor(getColor(R.color.red))
      }

      in "17.0".."18.4" -> {
        imcClassification.text = getString(R.string.label_abaixo_do_peso)
        imcClassification.setTextColor(getColor(R.color.yellow))
      }

      in "18.5".."24.9" -> {
        imcClassification.text = getString(R.string.label_normal)
        imcClassification.setTextColor(getColor(R.color.green))
      }

      in "25.0".."29.9" -> {
        imcClassification.text = getString(R.string.label_sobrepeso)
        imcClassification.setTextColor(getColor(R.color.yellow))
      }

      in "30.0".."34.9" -> {
        imcClassification.text = getString(R.string.label_obesidade_I)
        imcClassification.setTextColor(getColor(R.color.red))
      }

      in "35.0".."40.0" -> {
        imcClassification.text = getString(R.string.label_obesidade_II)
        imcClassification.setTextColor(getColor(R.color.red))
      }

      else -> {
        imcClassification.text = getString(R.string.label_obesidade_grave)
        imcClassification.setTextColor(getColor(R.color.red))
      }
    }
  }
}