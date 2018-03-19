package com.example.kinhangpoon.foodordering.utility

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kinhangpoon.foodordering.R
import com.example.kinhangpoon.foodordering.model.FoodItem
import com.squareup.picasso.Picasso

/**
 * Created by hefen on 3/18/2018.
 */
class MyFoodAdapter(internal var itemsList: List<FoodItem>, internal var context: Context) : RecyclerView.Adapter<MyFoodAdapter.MyViewHolder>(){
    private var itemModifier: ItemModifier? = null

    override fun getItemCount() = itemsList.size

    interface ItemModifier {
        fun onItemSelected(position: Int)
    }

    fun setItemModifier(itemModifier: ItemModifier) {
        this.itemModifier = itemModifier
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.food_item_layout, parent, false)
        //val v = LayoutInflater.from(parent.context).inflate(R.layout.place_item_layout, parent, false)

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
        val title = itemsList[position].foodName
        val category = itemsList[position].foodCategory
        val image = itemsList[position].foodThumb

        holder.titleTextView.text = title
        holder.categoryTextView.text = category
        Picasso.with(context).load(image).into(holder.imageViewMyImage)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var titleTextView: TextView
        internal var categoryTextView: TextView
        internal var imageViewMyImage: ImageView

        init {

            imageViewMyImage = itemView.findViewById(R.id.imgFood)
            titleTextView = itemView.findViewById(R.id.nameFood)
            categoryTextView = itemView.findViewById(R.id.categoryFood)
        }
    }
}