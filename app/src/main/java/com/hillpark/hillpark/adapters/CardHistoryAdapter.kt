package com.hillpark.hillpark.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hillpark.hillpark.AppClass
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.model.CardOperation
import kotlinx.android.synthetic.main.item_history_operation.view.*

class CardHistoryAdapter(private val list: List<CardOperation>) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NotificationViewHolder(LayoutInflater.from((parent.context)).inflate(R.layout.item_history_operation, parent, false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.operTitleLabel.text = list[position].title
        holder.itemView.sumLabel.text = list[position].amount
        holder.itemView.timeLabel.text = list[position].time
        holder.itemView.dateLabel.text = list[position].date
        if(list[position].amount.contains("-")){
            holder.itemView.sumLabel.setTextColor(AppClass.getContext().resources.getColor(R.color.minus_color))
            holder.itemView.iconOperation.setImageDrawable(AppClass.getContext().resources.getDrawable(R.drawable.ic_minus_red))
        }else{
            holder.itemView.sumLabel.setTextColor(AppClass.getContext().resources.getColor(R.color.plus_color))
            holder.itemView.iconOperation.setImageDrawable(AppClass.getContext().resources.getDrawable(R.drawable.ic_plus_green))
        }
    }

    class NotificationViewHolder(v: View) : RecyclerView.ViewHolder(v)
}