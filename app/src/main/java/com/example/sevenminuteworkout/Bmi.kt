package com.example.sevenminuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.sevenminuteworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class Bmi : AppCompatActivity() {
    val metri_unit_view="METRIC_UNIT_VIEW"
    val us_unit_view="US_UNIT_VIEW"
    var currentVisibleView:String=metri_unit_view
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
           if(currentVisibleView.equals(metri_unit_view)){
               if(validateMetricUnits()){
                   val heightValue:Float=binding.etMetricUnitHeight.text.toString().toFloat()/100
                   val weightValue:Float=binding.etMetricUnitWeight.text.toString().toFloat()
                   val bmi=weightValue/(heightValue*heightValue)

                   displaybmiResult(bmi)
               }else{
                   Toast.makeText(this,"invalid or empty input",Toast.LENGTH_LONG).show()
               }
           }else{
               if(validateUsUnits()){
                   val feetValue: Float =binding.etUsUnitHeightFeet.text.toString().toFloat()
                   val inchValue: Float =binding.etUsUnitHeightInch.text.toString().toFloat()
                   val weightValue:Float=binding.etUsUnitWeight.text.toString().toFloat()
                   val heightValue=inchValue+feetValue*12
                   val bmi=weightValue/(heightValue*heightValue)
                   displaybmiResult(bmi)
               }else{
                   Toast.makeText(this,"invalid or empty input",Toast.LENGTH_LONG).show()
               }
           }
        }
        makeVisibleMetricUnitsView()
        binding.rgUnits.setOnCheckedChangeListener { radioGroup, i ->
            if(i==R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleUSUnitsView()
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
    private fun validateUsUnits():Boolean{
        var isValide=true
        when {
            binding.etUsUnitWeight.text.toString().isEmpty() -> {
                isValide=false
            }
            binding.etUsUnitHeightFeet.text.toString().isEmpty() -> {
                isValide=false
            }
            binding.etUsUnitHeightInch.text.toString().isEmpty() -> {
                isValide=false
            }
        }
        return isValide

    }
    fun displaybmiResult(bmi:Float){
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take care of your better! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }
        binding.llDiplayBMIResult.visibility=View.VISIBLE


        // This is used to round of the result value to 2 decimal values after "."
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding.tvBMIValue.text = bmiValue // Value is set to TextView
        binding.tvBMIType.text = bmiLabel // Label is set to TextView
        binding.tvBMIDescription.text = bmiDescription // Description is set to TextView

    }
    private fun makeVisibleMetricUnitsView(){

        currentVisibleView=metri_unit_view
        binding.tilMetricUnitHeight.visibility=View.VISIBLE
        binding.tilMetricUnitWeight.visibility=View.VISIBLE
        binding.etMetricUnitHeight.text!!.clear()
        binding.etMetricUnitHeight.text!!.clear()
        binding.tilUsUnitWeight.visibility=View.GONE
        binding.llUsUnitsHeight.visibility=View.GONE

        binding.llDiplayBMIResult.visibility=View.GONE
    }
    private fun makeVisibleUSUnitsView(){

        currentVisibleView=us_unit_view
        binding.tilMetricUnitHeight.visibility=View.GONE
        binding.tilMetricUnitWeight.visibility=View.GONE
binding.etUsUnitHeightFeet.text!!.clear()
        binding.etUsUnitWeight.text!!.clear()
        binding.etUsUnitHeightInch.text!!.clear()
        binding.tilUsUnitWeight.visibility=View.VISIBLE
        binding.llUsUnitsHeight.visibility=View.VISIBLE
binding.llUsUnitsView.visibility=View.VISIBLE
        binding.llDiplayBMIResult.visibility=View.GONE
    }
}