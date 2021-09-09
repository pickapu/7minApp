package com.example.sevenminuteworkout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenminuteworkout.databinding.ActivityHistoryBinding
import com.example.sevenminuteworkout.databinding.ItemHistoryRowBinding

class historyAdapter (val context: Context, val items:ArrayList<String>):
    RecyclerView.Adapter<historyAdapter.viewHolder>() {



    class viewHolder(binding: ItemHistoryRowBinding):RecyclerView.ViewHolder(binding.root){
        val historyItemMain=binding.historyitemmain
        val tvItem=binding.tvItem
        val tvPosition=binding.tvPosition

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding=ItemHistoryRowBinding.inflate(LayoutInflater.from(context),parent,false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       val date:String= items[position]
        holder.tvPosition.text=(position+1).toString()
        holder.tvItem.text=date


        if (position % 2 == 0) {
            holder.historyItemMain.setBackgroundColor(
                Color.parseColor("#EBEBEB")
            )
        } else {
            holder.historyItemMain.setBackgroundColor(
                Color.parseColor("#FFFFFF")
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}