package com.hillpark.hillpark.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hillpark.hillparkApp.R

class NotificationAdapter :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType<3) return NotificationViewHolder(LayoutInflater.from((parent.context)).inflate(R.layout.item_notification_operation, parent, false))
        else return NotificationViewHolder(LayoutInflater.from((parent.context)).inflate(R.layout.item_notification_common, parent, false))
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    class NotificationViewHolder(v: View) : RecyclerView.ViewHolder(v)
}