package com.example.albumapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.albumapplication.domain.models.Album
import com.example.albumapplication.R
import com.example.albumapplication.interfaces.ItemClickListener
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val albumList: List<Album>, val itemClickListener: ItemClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view, itemClickListener)
    }
    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(albumList[position])
    }
    override fun getItemCount(): Int {
        return albumList.size
    }
    class ViewHolder(itemView: View, val itemClickListener: ItemClickListener) : RecyclerView.ViewHolder(itemView) {
        fun bind(album: Album) {
            val txtAlbumTitle = itemView.findViewById<TextView>(R.id.title)
            val imgAlbum = itemView.findViewById<ImageView>(R.id.image)

            txtAlbumTitle.text = album.title

            //load album's image
            Picasso.get()
                .load(album.thumbnailUrl)
                .placeholder(R.drawable.ic_baseline_image_not_supported_24)
                .error(R.drawable.ic_baseline_image_not_supported_24)
                .into(imgAlbum)

            itemView.setOnClickListener {
                itemClickListener.onItemClickListener(album)
            }
        }
    }
}