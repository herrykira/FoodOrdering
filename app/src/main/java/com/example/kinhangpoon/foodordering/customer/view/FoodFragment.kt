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
import com.example.kinhangpoon.foodordering.main.presenter.FoodPresenter
import com.example.kinhangpoon.foodordering.model.FoodItem
import com.example.kinhangpoon.foodordering.utility.AccountDescription
import com.example.kinhangpoon.foodordering.utility.FoodDescription
import com.example.kinhangpoon.foodordering.utility.SendMessage
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
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

    private var foodPresenter: FoodPresenter?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        rootView = inflater!!.inflate(R.layout.fragment_food, container, false)
        //Log.i("mylog", "on create fragment");
        initFood()

        return rootView
    }

    fun initFood() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Chicago"))

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
        btnOrder!!.setOnClickListener{//hit order btn, order food
            //Log.i("mylog", "order num " + editTextNum!!.text)
            var order_quantity = Integer.parseInt(editTextNum!!.text.toString())
            var order_category = foodItem!!.foodCategory
            var order_name = foodItem!!.foodName
            var order_price = Integer.parseInt(foodItem!!.foodPrice)
            var total_order: String? = null
            var order_delivery_add: String = if (FoodDescription.address != null) FoodDescription.address!! else "noida"

            //order_date = Date().toString()
            //Log.i("mylog", "order time " + order_date)
            /*sendOrder(order_category: String, order_name: String, order_quantity: String,
                    total_order: String, order_delivery_add: String, order_date: String, user_phone: String)*/
            if (order_quantity > 0) {
                val f = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                order_date = f.format(Date())
                total_order = (Integer.parseInt(order_quantity.toString()) * order_price).toString()
                Log.i("mylog", "food category " + order_category)
                Log.i("mylog", "food name " + order_name)
                Log.i("mylog", "food quantity " + order_quantity)
                Log.i("mylog", "food total " + total_order)
                Log.i("mylog", "food add " + order_delivery_add)
                Log.i("mylog", "food date " + order_date)
                Log.i("mylog", "food user " + AccountDescription.UserMobile)
                foodPresenter = FoodPresenter(context!!)
                foodPresenter!!.sendOrder(order_category,order_name,(order_quantity).toString(),
                        total_order,order_delivery_add,order_date!!,AccountDescription.UserMobile)
            } else {
                Log.i("mylog", "didn't order anything")
            }
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