package com.ritier.my_memo.View.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ritier.my_memo.R
import com.ritier.my_memo.View.Interface.OnListItemClickListener

class ImageAdapter(val context: Context) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    val imageList = mutableListOf<String>()
    lateinit var onListItemClickListener: OnListItemClickListener

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        val iv_image = itemView.findViewById<ImageView>(R.id.iv_image)

        override fun onClick(v: View?) {
            onListItemClickListener.onItemClick(itemView, adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
    }

    override fun getItemCount(): Int = imageList.size


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imagePath = imageList[position]

        Glide.with(context)
            .load(imagePath)
            .error(R.drawable.broken)
            .into(holder.iv_image)
    }

    fun setOnImageClickListener(onListItemClickListener: OnListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener
    }

    fun setAllImageData(imageList: MutableList<String>) {
        imageList.addAll(imageList)
        notifyDataSetChanged()
    }

    fun addImage(imagePath: String) {
        imageList.add(imagePath)
        notifyDataSetChanged()
    }

    fun deleteImage(i: Int) {
        imageList.removeAt(i)
        notifyDataSetChanged()
        Toast.makeText(context, "이미지가 삭제되었습니다.", Toast.LENGTH_SHORT).show()
    }

    fun getImages(): MutableList<String> {
        return imageList
    }
}