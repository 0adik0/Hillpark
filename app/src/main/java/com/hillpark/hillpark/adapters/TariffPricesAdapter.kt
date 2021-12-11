package com.hillpark.hillpark.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.model.TariffItemPriceModel
import kotlinx.android.synthetic.main.item_tariff_price.view.*

class TariffPricesAdapter(private val list: List<TariffItemPriceModel>) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TariffsCategoriesViewHolder(LayoutInflater.from((parent.context)).inflate(R.layout.item_tariff_price, parent, false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.name.text = list[position].name
        holder.itemView.weekTime.text = list[position].weekTime
        holder.itemView.age.text = list[position].age

        if(list[position].price1.contains(".")){
            holder.itemView.price.text = list[position].price1.substring(0, list[position].price1.indexOf("."))
        }else{
            holder.itemView.price.text = list[position].price1
        }

        if(list[position].price2.contains(".")){
            holder.itemView.price.text = holder.itemView.price.text.toString() + "/" + list[position].price2.substring(0, list[position].price2.indexOf("."))
        }else{
            holder.itemView.price.text = holder.itemView.price.text.toString() + "/" + list[position].price2
        }
    }

    class TariffsCategoriesViewHolder(v: View) : RecyclerView.ViewHolder(v)
}