package com.example.sevenminuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sevenminuteworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class Bmi : AppCompatActivity() {
    private lateinit var binding:ActivityBmiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityBmiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.actionBarBmi)
        val actionbar=supportActionBar
        if(actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title="Calulate BMI"
        }
        binding.actionBarBmi.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.btnCalculateUnits.setOnClickListener {
            if(validateMetricUnits()){
               val heightValue:Float=binding.etMetricUnitHeight.text.toString().toFloat()/100
               val weightValue:Float=binding.etMetricUnitWeight.text.toString().toFloat()
               val bmi=weightValue/(heightValue*heightValue)
                displaybmiResult(bmi)
        }else{
            Toast.makeText(this,"invalid or empty input",Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun validateMetricUnits():Boolean{
        var isValide=true
        if(binding.etMetricUnitWeight.text.toString().isEmpty()){
            isValide=false
        }else if(binding.etMetricUnitHeight.text.toString().isEmpty()){
            isValide=false
        }
        return isValide

    }
    fun displaybmiResult(bmi:Float){
        val bmiLabel: String
        val bmiDescription: String

        if (java.lang.Float.compare(bmi, 15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (java.lang.Float.compare(bmi, 15f) > 0 && java.lang.Float.compare(bmi,16f) <= 0) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (java.lang.Float.compare(bmi, 16f) > 0 && java.lang.Float.compare(bmi, 18.5f)<= 0) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (java.lang.Float.compare(bmi, 18.5f) > 0 && java.lang.Float.compare(bmi, 25f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(bmi, 30f) <= 0) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (java.lang.Float.compare(bmi, 30f) > 0 && java.lang.Float.compare(bmi, 35f) <= 0) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (java.lang.Float.compare(bmi, 35f) > 0 && java.lang.Float.compare(bmi, 40f) <= 0) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        binding.tvYourBMI.visibility = View.VISIBLE
        binding.tvBMIValue.visibility = View.VISIBLE
        binding.tvBMIType.visibility = View.VISIBLE
        binding.tvBMIDescription.visibility = View.VISIBLE

        // This is used to round of the result value to 2 decimal values after "."
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding.tvBMIValue.text = bmiValue // Value is set to TextView
        binding.tvBMIType.text = bmiLabel // Label is set to TextView
        binding.tvBMIDescription.text = bmiDescription // Description is set to TextView

    }
}