package com.hillpark.hillpark.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.model.ActionModel
import kotlinx.android.synthetic.main.item_action.view.*
import android.graphics.BitmapFactory
import android.util.Base64
import com.hillpark.hillpark.mvp.fragments.ActionsFragment
import com.hillpark.hillpark.utils.javaUtils
import java.util.*


class ActionsAdapter(private val list: List<ActionModel>, private val fragment: ActionsFragment) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ActionsViewHolder(LayoutInflater.from((parent.context)).inflate(R.layout.item_action, parent, false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.title.text = list[position].title
        val decoded = Base64.decode(list[position].image64, Base64.DEFAULT)
        val image = BitmapFactory.decodeByteArray(decoded, 0, decoded.size)
        holder.itemView.imageContent.setImageBitmap(image)
        holder.itemView.imageContent.setOnClickListener { fragment.imageClicked(list[position].link) }
    }

    class ActionsViewHolder(v: View) : RecyclerView.ViewHolder(v)
}