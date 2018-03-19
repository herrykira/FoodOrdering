package com.example.kinhangpoon.foodordering.customer.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kinhangpoon.foodordering.R
import com.example.kinhangpoon.foodordering.utility.FoodDescription
import com.example.kinhangpoon.foodordering.utility.MyFoodAdapter
import com.example.kinhangpoon.foodordering.utility.SendMessage

/**
 * Created by hefen on 3/18/2018.
 */
class MenuFragment() : Fragment(), MyFoodAdapter.ItemModifier{
    internal var city: String? = null
    internal var sendMessage: SendMessage? = null
    internal var rootView: View? = null
    internal var context: Context? = null
    internal var recyclerViewItems: RecyclerView? = null
    internal var adapter: MyFoodAdapter? = null

    override fun onItemSelected(position: Int) {
        //Log.i("mylog", "item " + position)
        sendMessage!!.sendData(position)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        rootView = inflater!!.inflate(R.layout.fragment_menu, container, false)
        //Log.i("mylog", "on create fragment");
        initMenu()

        return rootView
    }

    fun initMenu() {
        context = rootView!!.context

        recyclerViewItems = rootView!!.findViewById(R.id.recyclerView3)
        recyclerViewItems!!.setLayoutManager(StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL))
        //recyclerViewItems!!.setLayoutManager(GridLayoutManager(context, 2))
        recyclerViewItems!!.setHasFixedSize(true)

        if (city == "delhi" && FoodDescription.foodListDelhi != null) {//?????
            adapter = MyFoodAdapter(FoodDescription.foodListDelhi!!, context!!)
            adapter!!.setItemModifier(this)//??this context
            recyclerViewItems!!.setAdapter(adapter)
        } else if (city == "banglore" && FoodDescription.foodListBanglore != null) {
            adapter = MyFoodAdapter(FoodDescription.foodListBanglore!!, context!!)
            adapter!!.setItemModifier(this)//??this context
            recyclerViewItems!!.setAdapter(adapter)
        }

    }
    override fun onResume() {
        super.onResume()
        sendMessage!!.setTitle("Menu")
    }

    fun setSendMessage(sendMessage: SendMessage) {
        this.sendMessage = sendMessage
    }

    fun setCity(city: String) {
        this.city = city
    }
}