package com.example.kinhangpoon.foodordering.customer.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.kinhangpoon.foodordering.R
import com.example.kinhangpoon.foodordering.model.FoodItem
import com.example.kinhangpoon.foodordering.utility.FoodDescription
import com.example.kinhangpoon.foodordering.utility.SendMessage
import com.squareup.picasso.Picasso
import java.util.*

/**
 * Created by hefen on 3/18/2018.
 */
class FoodFragment : Fragment() {
    internal var sendMessage: SendMessage? = null
    internal var rootView: View? = null
    internal var context: Context? = null
    internal var city: String? = null
    internal var order_date: String? = null
    internal var indx: Int? = null
    internal var num = 0

    internal var textViewName: TextView? = null
    internal var textViewID: TextView? = null
    internal var textViewPrice: TextView? = null
    internal var textViewRecipiee: TextView? = null
    internal var textViewCategory: TextView? = null
    internal var editTextNum: EditText? = null
    internal var imageViewDe: ImageView? = null
    internal var imageViewIn: ImageView? = null
    internal var imageViewFood: ImageView? = null
    internal var btnOrder: Button? = null

    internal var foodItem: FoodItem? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        rootView = inflater!!.inflate(R.layout.fragment_food, container, false)
        //Log.i("mylog", "on create fragment");
        initFood()

        return rootView
    }

    fun initFood() {
        context = rootView!!.context
        textViewName = rootView!!.findViewById(R.id.textView2)
        textViewID = rootView!!.findViewById(R.id.textView4)
        textViewPrice = rootView!!.findViewById(R.id.textView3)
        textViewRecipiee = rootView!!.findViewById(R.id.textView5)
        textViewCategory = rootView!!.findViewById(R.id.textView6)
        editTextNum = rootView!!.findViewById(R.id.editText)
        imageViewFood = rootView!!.findViewById(R.id.imageView5)
        imageViewDe = rootView!!.findViewById(R.id.imageViewDe)
        imageViewIn = rootView!!.findViewById(R.id.imageViewIn)
        btnOrder = rootView!!.findViewById(R.id.button2)

        editTextNum!!.setText("0",TextView.BufferType.EDITABLE)

        imageViewDe!!.setOnClickListener{
            num = Integer.parseInt(editTextNum!!.getText().toString())
            if (num > 0) {
                num -= 1
                editTextNum!!.setText("" + num,TextView.BufferType.EDITABLE)
            }
        }
        imageViewIn!!.setOnClickListener{
            num = Integer.parseInt(editTextNum!!.getText().toString())
            num += 1
            editTextNum!!.setText("" + num,TextView.BufferType.EDITABLE)
        }
        btnOrder!!.setOnClickListener{
            Log.i("mylog", "order num " + editTextNum!!.text)
            order_date = Date().toString()

        }

        if (city != null && indx != null && city == "banglore") {
            foodItem = FoodDescription.foodListBanglore!![indx!!]
        } else if (city != null && indx != null && city == "delhi") {
            foodItem = FoodDescription.foodListDelhi!![indx!!]
        }

        if (foodItem != null) {
            textViewName!!.text = foodItem!!.foodName
            textViewID!!.text = "ID: " + foodItem!!.foodId
            textViewPrice!!.text = "\u20B9 " + foodItem!!.foodPrice
            textViewCategory!!.text = foodItem!!.foodCategory
            textViewRecipiee!!.text = foodItem!!.foodRecepiee
            Picasso.with(context).load(foodItem!!.foodThumb).into(imageViewFood)
        }
    }
    override fun onResume() {
        super.onResume()
        sendMessage!!.setTitle("Order Your Food")
    }

    fun setSendMessage(sendMessage: SendMessage) {
        this.sendMessage = sendMessage
    }

    fun setCity(city: String) {
        this.city = city
    }
    fun setIndex(indx: Int?) {
        this.indx = indx
    }
}