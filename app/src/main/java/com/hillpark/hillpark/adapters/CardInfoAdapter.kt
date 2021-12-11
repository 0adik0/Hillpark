package com.hillpark.hillpark.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hillpark.hillpark.AppClass
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.model.CardInfo
import kotlinx.android.synthetic.main.item_card_info.view.*

class CardInfoAdapter(private val cardInfo: CardInfo) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NotificationViewHolder(LayoutInflater.from((parent.context)).inflate(R.layout.item_card_info, parent, false))

    }

    override fun getItemCount(): Int {
        return 7
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(position){
            0 -> {holder.itemView.label.text = AppClass.getContext().resources.getString(R.string.card_edit_id); holder.itemView.infoTitle.text = cardInfo.id}
            1 -> {holder.itemView.label.text = AppClass.getContext().resources.getString(R.string.card_edit_category); holder.itemView.infoTitle.text = cardInfo.category}
            2 -> {holder.itemView.label.text = AppClass.getContext().resources.getString(R.string.card_edit_type); holder.itemView.infoTitle.text = cardInfo.type}
            3 -> {holder.itemView.label.text = AppClass.getContext().resources.getString(R.string.card_edit_tariff); holder.itemView.infoTitle.text = cardInfo.tatif}
            4 -> {
                holder.itemView.label.text = AppClass.getContext().resources.getString(R.string.card_edit_work_from)
                val date = cardInfo.workFrom
                holder.itemView.infoTitle.text = date[6].toString() + date[7].toString() + "." + date[4].toString() + date[5].toString() + "." + date[0].toString() + date[1].toString() + date[2].toString() + date[3].toString()
            }
            5 -> {
                holder.itemView.label.text = AppClass.getContext().resources.getString(R.string.card_edit_work_to);
                val date = cardInfo.workTo
                holder.itemView.infoTitle.text = date[6].toString() + date[7].toString() + "." + date[4].toString() + date[5].toString() + "." + date[0].toString() + date[1].toString() + date[2].toString() + date[3].toString()
            }
            6 -> {
                holder.itemView.label.text = AppClass.getContext().resources.getString(R.string.card_edit_balance)
                if(cardInfo.balance!="") {
                    if(cardInfo.balance.contains(".")) {
                        holder.itemView.infoTitle.text = cardInfo.balance.substring(0, cardInfo.balance.indexOf(".")) + " " + "руб"
                    }else{
                        holder.itemView.infoTitle.text = cardInfo.balance + " " + "руб"
                    }
                }else{
                    holder.itemView.infoTitle.text = "0 руб"
                }
                holder.itemView.infoTitle.setTextColor(AppClass.getContext().resources.getColor(R.color.colorAccent))
            }

        }
    }

    class NotificationViewHolder(v: View) : RecyclerView.ViewHolder(v)
}