package com.hillpark.hillpark.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.model.Card
import com.hillpark.hillpark.mvp.fragments.MyCardsFragment
import kotlinx.android.synthetic.main.item_card_edit.view.*

class MyCardsAdapter(private val list : ArrayList<Card>, private val fragment: MyCardsFragment) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var posToDel = 0
    private lateinit var holder: RecyclerView.ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyCardsViewHolder(LayoutInflater.from((parent.context)).inflate(R.layout.item_card_edit, parent, false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.cardTitle.text = list[position].name
        holder.itemView.setOnClickListener { fragment.cardClicked(list[position].name,list[position].code)}
        holder.itemView.deleteBtn.setOnClickListener {
            this.holder = holder
            posToDel = position
            fragment.openRemoveDialog(code = list[position].code, name = list[position].name)
        }
    }

    fun removeCardFromList(){
        val newPosition = holder.getAdapterPosition()
        list.removeAt(posToDel)
        notifyItemRemoved(posToDel)
        notifyItemRangeChanged(newPosition, list.size)
    }

    class MyCardsViewHolder(v: View) : RecyclerView.ViewHolder(v)
}