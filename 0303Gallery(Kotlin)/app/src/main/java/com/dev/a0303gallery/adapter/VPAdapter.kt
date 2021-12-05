package com.dev.a0303gallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.a0303gallery.R
import com.dev.a0303gallery.data.PhotoItem
import kotlinx.android.synthetic.main.vp_item.view.*

class VPAdapter : ListAdapter<PhotoItem, VP_VH>(DIFFPHOTOCALLBACK) {

    object DIFFPHOTOCALLBACK : DiffUtil.ItemCallback<PhotoItem>() {
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.photoId == newItem.photoId
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VP_VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vp_item, parent, false)

        return VP_VH(view)
    }

    override fun onBindViewHolder(holder: VP_VH, position: Int) {
        Glide.with(holder.itemView)
                .load(getItem(position).previewUrl)
                .placeholder(R.drawable.placeholder_item)
                .into(holder.itemView.imageView2)
    }

}


class VP_VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

}