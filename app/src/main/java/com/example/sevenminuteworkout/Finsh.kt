package com.example.sevenminuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sevenminuteworkout.databinding.ActivityFinshBinding
import java.text.SimpleDateFormat
import java.util.*

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
        addDateToDatabase()
    }
    private fun  addDateToDatabase(){
        val calender=Calendar.getInstance()
        val dateTime=calender.time
        Log.i("Date:",""+dateTime)

        val sdf=SimpleDateFormat("dd MM yyyy HH:mm:ss",Locale.getDefault())
        val date=sdf.format(dateTime)

        val dbHandler=SqliteOpenHelper(this,null)
        dbHandler.addDate(date)

    }
}