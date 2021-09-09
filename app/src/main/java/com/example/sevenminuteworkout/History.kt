package com.example.sevenminuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
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
        getAllCompletedDate()
    }
    private fun getAllCompletedDate(){
        val dbHAndler=SqliteOpenHelper(this,null)
        val allCompleteDatelist=dbHAndler.getAllCompletedDateList()
        for(i in allCompleteDatelist){
            if(allCompleteDatelist.size>0){
                binding.tvHistory.visibility=View.VISIBLE
                binding.rvHistory.visibility=View.VISIBLE
                binding.tvNoDataAvailable.visibility=View.GONE
                binding.rvHistory.layoutManager=LinearLayoutManager(this)
                val hstryAdapter=historyAdapter(this,allCompleteDatelist)
                binding.rvHistory.adapter=hstryAdapter
            }else{
                binding.tvHistory.visibility=View.GONE
                binding.rvHistory.visibility=View.GONE
                binding.tvNoDataAvailable.visibility=View.VISIBLE
            }
        }
    }
}