package com.hillpark.hillpark.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hillpark.hillpark.model.CameraUrl
import com.hillpark.hillpark.mvp.fragments.OnlineCamerasFragment
import com.hillpark.hillparkApp.R
import kotlinx.android.synthetic.main.item_camera.view.*

class CamerasAdapter(private val list : List<CameraUrl>, private val fragment: OnlineCamerasFragment) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CamerasViewHolder(LayoutInflater.from((parent.context)).inflate(R.layout.item_camera, parent, false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.cameraName.text = list[position].name
        holder.itemView.camBtn.setOnClickListener { Log.e("cam", "clk");fragment.cameraClicked(list[position].url)}
    }

    class CamerasViewHolder(v: View) : RecyclerView.ViewHolder(v)
}