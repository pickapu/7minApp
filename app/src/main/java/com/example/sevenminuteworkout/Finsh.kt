package com.example.sevenminuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sevenminuteworkout.databinding.ActivityFinshBinding

class Finsh : AppCompatActivity() {
    private lateinit var bind:ActivityFinshBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityFinshBinding.inflate(layoutInflater)
        setContentView(bind.root)

        setSupportActionBar(bind.actionBarfinsh)
        val actionbar=supportActionBar
        if(actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        bind.actionBarfinsh.setNavigationOnClickListener {
            onBackPressed()
        }
        bind.finshBtn.setOnClickListener{
            finish()
        }
    }
}