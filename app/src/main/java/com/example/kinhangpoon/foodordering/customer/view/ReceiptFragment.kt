package com.example.kinhangpoon.foodordering.customer.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kinhangpoon.foodordering.R
import com.example.kinhangpoon.foodordering.utility.FoodDescription
import com.example.kinhangpoon.foodordering.utility.SendMessage

/**
 * Created by hefen on 3/20/2018.
 */
class ReceiptFragment: Fragment() {
    internal var sendMessage: SendMessage? = null
    internal var rootView: View? = null
    internal var context: Context? = null

    internal var textViewReceiptID: TextView? = null
    internal var textViewReceiptName: TextView? = null
    internal var textViewReceiptQuan: TextView? = null
    internal var textViewReceiptTotal: TextView? = null
    internal var textViewReceiptAdd: TextView? = null
    internal var textViewReceiptDate: TextView? = null

    internal var textViewTrackID: TextView? = null
    internal var textViewTrackTotal: TextView? = null
    internal var textViewTrackStatus: TextView? = null
    internal var textViewTrackDate: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        rootView = inflater!!.inflate(R.layout.fragment_receipt, container, false)
        //Log.i("mylog", "on create fragment");
        initReceipt()

        return rootView
    }

    fun initReceipt() {
        context = rootView!!.context
        val status: String
        when(FoodDescription.trackOrderDetail!!.orderStatus) {
            "1" -> status = "Packing"
            "2" -> status = "On the Way"
            "3" -> status = "Delivered"
            else -> status = "Unknown"
        }

        textViewReceiptID = rootView!!.findViewById(R.id.textView24)
        textViewReceiptName = rootView!!.findViewById(R.id.textView25)
        textViewReceiptQuan = rootView!!.findViewById(R.id.textView26)
        textViewReceiptTotal = rootView!!.findViewById(R.id.textView27)
        textViewReceiptAdd = rootView!!.findViewById(R.id.textView28)
        textViewReceiptDate = rootView!!.findViewById(R.id.textView29)

        textViewTrackID = rootView!!.findViewById(R.id.textView32)
        textViewTrackTotal = rootView!!.findViewById(R.id.textView30)
        textViewTrackStatus = rootView!!.findViewById(R.id.textView35)
        textViewTrackDate = rootView!!.findViewById(R.id.textView37)

        textViewReceiptName!!.text = FoodDescription.confirmOrderDetail!!.orderName
        textViewReceiptID!!.text = FoodDescription.confirmOrderDetail!!.orderId
        textViewReceiptQuan!!.text = FoodDescription.confirmOrderDetail!!.orderQuantity
        textViewReceiptTotal!!.text = FoodDescription.confirmOrderDetail!!.totalOrder
        textViewReceiptAdd!!.text = FoodDescription.confirmOrderDetail!!.orderDeliverAdd
        textViewReceiptDate!!.text = FoodDescription.confirmOrderDetail!!.orderDate

        textViewTrackID!!.text = FoodDescription.trackOrderDetail!!.orderId
        textViewTrackTotal!!.text = FoodDescription.trackOrderDetail!!.totalOrder
        textViewTrackStatus!!.text = status
        textViewTrackDate!!.text = FoodDescription.trackOrderDetail!!.orderDate
    }

    fun setSendMessage(sendMessage: SendMessage) {
        this.sendMessage = sendMessage
    }

    override fun onResume() {
        super.onResume()
        sendMessage!!.setTitle("Your Receipt")
    }
}