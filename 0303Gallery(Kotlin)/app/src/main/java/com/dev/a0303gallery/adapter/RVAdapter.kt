package com.dev.a0303gallery.adapter

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.dev.a0303gallery.R
import com.dev.a0303gallery.data.PhotoItem
import kotlinx.android.synthetic.main.rv_item.view.*

class RVAdapter : ListAdapter<PhotoItem, RV_VH>(DIFFCALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RV_VH {
        val holder = RV_VH(LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false))
        holder.itemView.setOnClickListener {
            Bundle().apply {
                putParcelableArrayList("PhotoItem", ArrayList(currentList))
                putInt("PhotoPosition", holder.adapterPosition)
                holder.itemView.findNavController().navigate(R.id.action_galleryFragment_to_pagerFragment, this)
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: RV_VH, position: Int) {
        val photoItem = getItem(position)
        with(holder.itemView) {
            shimmerLayout.apply {
                setShimmerColor(0x55FFFFFF)
                setShimmerAngle(0)
                startShimmerAnimation()
            }
            tv_users.text = photoItem.photoUser
            tv_likes.text = photoItem.likes.toString()
            tv_fav.text = photoItem.favorites.toString()
            imageView.layoutParams.height = photoItem.photoHeight
        }
        Log.d("TAG", photoItem.previewUrl)

        Glide.with(holder.itemView)
                .load(photoItem.previewUrl)
                .placeholder(R.drawable.placeholder_item)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false.also {
                            holder.itemView.shimmerLayout?.stopShimmerAnimation()
                        }
                    }

                })
                .into(holder.itemView.imageView)
    }

    object DIFFCALLBACK : DiffUtil.ItemCallback<PhotoItem>() {
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.photoId == newItem.photoId
        }
    }
}


class RV_VH(itemView: View) : RecyclerView.ViewHolder(itemView)
