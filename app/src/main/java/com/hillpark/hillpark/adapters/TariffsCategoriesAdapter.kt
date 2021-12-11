package com.hillpark.hillpark.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.model.TariffCategoryModel
import com.hillpark.hillpark.mvp.fragments.TariffsFragment
import kotlinx.android.synthetic.main.item_tariff_category.view.*

class TariffsCategoriesAdapter(private val list: List<TariffCategoryModel>, private val fragment: TariffsFragment) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TariffsCategoriesViewHolder(LayoutInflater.from((parent.context)).inflate(R.layout.item_tariff_category, parent, false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.title.text = list[position].title
        holder.itemView.tariffBtn.setOnClickListener { fragment.tariffClicked(list[position].id, list[position].title) }
    }

    class TariffsCategoriesViewHolder(v: View) : RecyclerView.ViewHolder(v)
}