package com.example.kinhangpoon.foodordering.utility

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kinhangpoon.foodordering.R

/**
 * Created by hefen on 3/19/2018.
 */
class MyRecordAdapter(): RecyclerView.Adapter<MyRecordAdapter.MyViewHolder>() {
    //private var itemModifier: ItemModifier? = null

    override fun getItemCount() = FoodDescription.recordList!!.size

    interface ItemModifier {
        fun onItemSelected(position: Int)
    }

    /*fun setItemModifier(itemModifier: ItemModifier) {
        this.itemModifier = itemModifier
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.record_item_layout, parent, false)

        val myViewHolder = MyViewHolder(v)

        /*v.setOnClickListener(View.OnClickListener {
            if (itemModifier != null) {
                itemModifier!!.onItemSelected(myViewHolder.getAdapterPosition())
            }
        })*/

        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //val item = itemsList[position]
        val id = "Order ID: " + FoodDescription.recordList!![position].orderId
        val name = FoodDescription.recordList!![position].orderName
        val num = "Quantity: " + FoodDescription.recordList!![position].orderQuantity
        val total = "\u20B9 " + FoodDescription.recordList!![position].totalOrder
        val status: String
        when(FoodDescription.recordList!![position].orderStatus) {
            "1" -> status = "Packing"
            "2" -> status = "On the Way"
            "3" -> status = "Delivered"
            else -> status = "Unknown"
        }
        val add = "Address: " + FoodDescription.recordList!![position].orderDeliverAdd
        val date = "Order Date: " + FoodDescription.recordList!![position].orderDate

        holder.idTextView.text = id
        holder.nameTextView.text = name
        holder.numTextView.text = num
        holder.totalTextView.text = total
        holder.statusTextView.text = status
        holder.addTextView.text = add
        holder.dateTextView.text = date
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var idTextView: TextView
        internal var nameTextView: TextView
        internal var numTextView: TextView
        internal var totalTextView: TextView
        internal var statusTextView: TextView
        internal var addTextView: TextView
        internal var dateTextView: TextView

        init {
            idTextView = itemView.findViewById(R.id.textView7)
            nameTextView = itemView.findViewById(R.id.textView8)
            numTextView = itemView.findViewById(R.id.textView9)
            totalTextView = itemView.findViewById(R.id.textView10)
            statusTextView = itemView.findViewById(R.id.textView11)
            addTextView = itemView.findViewById(R.id.textView12)
            dateTextView = itemView.findViewById(R.id.textView13)
        }
    }
}