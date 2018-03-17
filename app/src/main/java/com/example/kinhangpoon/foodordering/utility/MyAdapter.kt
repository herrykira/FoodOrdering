package com.example.kinhangpoon.foodordering.utility

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kinhangpoon.foodordering.R

/**
 * Created by hefen on 3/17/2018.
 */
class MyAdapter() : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    internal var titles = arrayOf("Menu", "Track", "Ordering Record", "Map")
    internal var icons = arrayOf(R.drawable.menu, R.drawable.track, R.drawable.orderhistory, R.drawable.map)

    private var itemModifier: ItemModifier? = null

    override fun getItemCount() = titles.size

    interface ItemModifier {
        fun onItemSelected(position: Int)
    }

    fun setItemModifier(itemModifier: ItemModifier) {
        this.itemModifier = itemModifier
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.mainscreen_item_layout, parent, false)

        val myViewHolder = MyViewHolder(v)

        v.setOnClickListener(View.OnClickListener {
            if (itemModifier != null) {
                itemModifier!!.onItemSelected(myViewHolder.getAdapterPosition())
            }
        })

        return myViewHolder
        //return new MyViewHolder(v);
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //val item = itemsList[position]
        val title = titles[position]
        val image = icons[position]

        holder.titleTextView.text = title
        holder.imageViewMyImage.setImageResource(image)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var titleTextView: TextView
        internal var imageViewMyImage: ImageView

        init {

            imageViewMyImage = itemView.findViewById(R.id.imageView2)
            titleTextView = itemView.findViewById(R.id.textViewTitle)

        }
    }
}