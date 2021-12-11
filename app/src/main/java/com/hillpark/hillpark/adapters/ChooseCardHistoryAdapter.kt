package com.hillpark.hillpark.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.model.Card
import com.hillpark.hillpark.mvp.fragments.HistoryFragment
import kotlinx.android.synthetic.main.item_card_edit.view.*

class ChooseCardHistoryAdapter(private val list : List<Card>, private val fragment: HistoryFragment) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyCardsViewHolder(LayoutInflater.from((parent.context)).inflate(R.layout.item_choose_card, parent, false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.cardTitle.text = list[position].name
        holder.itemView.setOnClickListener { fragment.cardClicked(list[position].code)}
    }

    class MyCardsViewHolder(v: View) : RecyclerView.ViewHolder(v)
}