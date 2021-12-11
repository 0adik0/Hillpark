package com.hillpark.hillpark.adapters

import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.TextView
import com.smarteist.autoimageslider.SliderViewAdapter
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.hillpark.hillpark.AppClass
import com.hillpark.hillpark.model.SliderItem
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.model.SliderServerItem
import com.hillpark.hillpark.mvp.view.MainFragmentView


class SliderAdapter(private val list: List<SliderItem>, private val fragment: MainFragmentView) : SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate =
            LayoutInflater.from(AppClass.getContext()).inflate(R.layout.image_slider_layout_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {

                viewHolder.textViewDescription.text = list[position].title
                Glide.with(viewHolder.itemView)
                    .asBitmap()
                    .load(list[position].image)
                    .fitCenter()
                    .into(viewHolder.imageViewBackground)
                viewHolder.itemView.setOnClickListener { fragment.openUrl(list[position].htmlUrl) }
    }

    override fun getCount(): Int {
        return list.size
    }

    class SliderAdapterVH(var itemView: View) :
        SliderViewAdapter.ViewHolder(itemView) {
        var imageViewBackground: ImageView
        var textViewDescription: TextView

        init {
            imageViewBackground = itemView.findViewById(R.id.image)
            textViewDescription = itemView.findViewById(R.id.title)
        }
    }
}