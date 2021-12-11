package com.hillpark.hillpark.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hillpark.hillpark.model.CardOrder
import com.hillpark.hillparkApp.R
import kotlinx.android.synthetic.main.item_my_order.view.*

class CardOrdersAdapter(private val list: List<CardOrder>) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CardOrdersViewHolder(LayoutInflater.from((parent.context)).inflate(R.layout.item_my_order, parent, false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val date = list[position].time
        holder.itemView.dateTitle.text = date[6].toString() + date[7].toString() + "." + date[4].toString() + date[5].toString() + "." +
                                         date[0].toString() + date[1].toString() + date[2].toString() + date[3].toString() +
                                         " " + date[9].toString() + date[10].toString() + ":" + date[11].toString() + date[12].toString()

        var price = list[position].price
        if(price.contains("."))price = price.substring(0, price.indexOf("."))
        holder.itemView.sumTitle.text = price + " руб"

        holder.itemView.mainTitle.text = list[position].title
    }

    class CardOrdersViewHolder(v: View) : RecyclerView.ViewHolder(v)
}