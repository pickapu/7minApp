package com.example.sevenminuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sevenminuteworkout.databinding.ActivityHistoryBinding

class History : AppCompatActivity() {
    private  lateinit var binding:ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityHistoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.actionBarHistory)
        val actionBar=supportActionBar
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title="History"
        }
        binding.actionBarHistory.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}