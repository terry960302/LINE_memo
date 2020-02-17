package com.ritier.my_memo.UI.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ritier.my_memo.Model.MemoModel
import com.ritier.my_memo.R
import com.ritier.my_memo.UI.DetailActivity
import com.ritier.my_memo.Util.getRandColor

class MemoAdapter(val context: Context) :
    RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {

    private val TAG = "MemoAdapter"
    private var list = mutableListOf<MemoModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_main_item, parent, false)
        return MemoViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MemoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cv_id = itemView.findViewById<CardView>(R.id.cv_id)
        val tv_id = itemView.findViewById<TextView>(R.id.tv_id)
        val cv_thumbnail = itemView.findViewById<CardView>(R.id.cv_thumbnail)
        val iv_thumbnail = itemView.findViewById<ImageView>(R.id.iv_thumbnail)
        val tv_title = itemView.findViewById<TextView>(R.id.tv_app_bar)
        val tv_desc = itemView.findViewById<TextView>(R.id.tv_desc)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {

        val id = list[position].id
        val thumbList = list[position].thumbPathList
        val title = list[position].title
        val desc = list[position].desc

        //아이디
        holder.tv_id.text = id.toString()
        holder.cv_id.setCardBackgroundColor(getRandColor())

        //제목
        holder.tv_title.text = title

        //내용
        holder.tv_desc.text = desc

        //썸네일
        if (thumbList != null && thumbList.isNotEmpty()) {
            Glide.with(context).load(thumbList[0]).into(holder.iv_thumbnail)
            holder.cv_thumbnail.visibility = View.VISIBLE
        } else {
            holder.cv_thumbnail.visibility = View.GONE
        }

        //뷰 클릭
        holder.itemView.setOnClickListener {
            val intent = Intent(context.applicationContext, DetailActivity::class.java)
            intent.putExtra("memoId",id)
            context.startActivity(intent)
        }
    }
    fun setData(memoList : MutableList<MemoModel>){
        this.list = memoList
        notifyDataSetChanged()
        Log.d(TAG, "메모장이 업데이트 되었습니다.")
    }
}