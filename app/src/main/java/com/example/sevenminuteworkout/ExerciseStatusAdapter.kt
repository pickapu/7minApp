package com.example.sevenminuteworkout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenminuteworkout.databinding.ItemExerciseStatusBinding
import com.example.sevenminuteworkout.databinding.ItemExerciseStatusBinding.inflate

class ExerciseStatusAdapter(val item:ArrayList<ExerciseModel>,val context:Context):
    RecyclerView.Adapter<ExerciseStatusAdapter.viewHolder>() {

    class viewHolder(binding:ItemExerciseStatusBinding):RecyclerView.ViewHolder(binding.root){
        val tvItem=binding.tvItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding=ItemExerciseStatusBinding.inflate(LayoutInflater.from(context),parent,false)

return viewHolder(binding)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val model:ExerciseModel=item[position]
        holder.tvItem.text=model.getId().toString()
        if(model.getSelected()){
            holder.tvItem.background=ContextCompat.getDrawable(context,R.drawable.item_circular_thin_color_acent_border)
            holder.tvItem.setTextColor(Color.parseColor("#00bcd4"))
        }else if(model.getCompleted()){
            holder.tvItem.background=ContextCompat.getDrawable(context,R.drawable.item_circular_color_grey_background)
            holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            holder.tvItem.background=ContextCompat.getDrawable(context,R.drawable.item_circular_color_grey_background)
            holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }

    override fun getItemCount(): Int {
return item.size
    }
}