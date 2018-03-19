package com.example.kinhangpoon.foodordering.utility

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kinhangpoon.foodordering.R

/**
 * Created by hefen on 3/18/2018.
 */
class MyPlacesAdapter() : RecyclerView.Adapter<MyPlacesAdapter.MyViewHolder>() {
    internal var titles = arrayOf("High Court of Karnataka", "Bangalore Palace", "St Francis Xavier", "Vikas Soudha",
                                    "Lotus Tempel", "Akshardham", "India Gate", "Tajmahal")
    internal var icons = arrayOf(R.drawable.bangalore_high_court_of_karnataka, R.drawable.bangalore_palace,
                                    R.drawable.bangalore_st_francis_xavier, R.drawable.bangalore_vikas_soudha,
                                    R.drawable.delhi_lotus_tempel, R.drawable.delhi_akshardham,
                                    R.drawable.delhi_india_gate, R.drawable.delhi_tajmahal)

    private var itemModifier: ItemModifier? = null

    override fun getItemCount() = titles.size

    interface ItemModifier {
        fun onItemSelected(position: Int)
    }

    fun setItemModifier(itemModifier: ItemModifier) {
        this.itemModifier = itemModifier
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.place_item_layout, parent, false)

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

            imageViewMyImage = itemView.findViewById(R.id.img)
            titleTextView = itemView.findViewById(R.id.img_name)

        }
    }
}