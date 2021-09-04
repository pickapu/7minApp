package com.example.sevenminuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sevenminuteworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.llstart.setOnClickListener {
            val intent=Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }
        binding.bmi.setOnClickListener {
            val intent=Intent(this,Bmi::class.java)
            startActivity(intent)
        }
    }
}