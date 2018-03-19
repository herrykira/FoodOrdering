package com.example.kinhangpoon.foodordering.customer.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dant.centersnapreyclerview.SnappingRecyclerView
import com.example.kinhangpoon.foodordering.R
import com.example.kinhangpoon.foodordering.utility.MyAdapter
import com.example.kinhangpoon.foodordering.utility.SendMessage

/**
 * Created by hefen on 3/17/2018.
 */
class MainscreenFragment: Fragment(), MyAdapter.ItemModifier{
    internal var sendMessage: SendMessage? = null
    internal var rootView: View? = null
    internal var context: Context? = null
    internal var recyclerViewItems: SnappingRecyclerView? = null
    internal var adapter: MyAdapter? = null

    override fun onItemSelected(position: Int) {
        //Log.i("mylog", "item " + position)
        sendMessage!!.sendData(position)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        rootView = inflater!!.inflate(R.layout.fragment_mainscreen, container, false)
        //Log.i("mylog", "on create fragment");
        initMainscreen()

        return rootView
    }

    fun initMainscreen() {
        context = rootView!!.context

        recyclerViewItems = rootView!!.findViewById(R.id.recyclerView1)
        recyclerViewItems!!.setLayoutManager(GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false))
        recyclerViewItems!!.setHasFixedSize(true)

        adapter = MyAdapter()
        adapter!!.setItemModifier(this)//??this context
        recyclerViewItems!!.setAdapter(adapter)
    }

    fun setSendMessage(sendMessage: SendMessage) {
        this.sendMessage = sendMessage
    }

    override fun onResume() {
        super.onResume()
        sendMessage!!.setTitle("Home")
    }
}