package com.example.kinhangpoon.foodordering.customer.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kinhangpoon.foodordering.R
import com.example.kinhangpoon.foodordering.utility.MyPlacesAdapter
import com.example.kinhangpoon.foodordering.utility.MyRecordAdapter
import com.example.kinhangpoon.foodordering.utility.SendMessage

/**
 * Created by hefen on 3/19/2018.
 */

class RecordFragment: Fragment() {
    internal var sendMessage: SendMessage? = null
    internal var rootView: View? = null
    internal var context: Context? = null
    internal var recyclerViewItems: RecyclerView? = null
    internal var adapter: MyRecordAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        rootView = inflater!!.inflate(R.layout.fragment_places, container, false)
        //Log.i("mylog", "on create fragment");
        initRecord()

        return rootView
    }

    fun initRecord(){
        context = rootView!!.context

        recyclerViewItems = rootView!!.findViewById(R.id.recyclerView2)
        recyclerViewItems!!.setLayoutManager(LinearLayoutManager(context))
        recyclerViewItems!!.setHasFixedSize(true)

        adapter = MyRecordAdapter()
        //adapter!!.setItemModifier(this)//??this context
        recyclerViewItems!!.setAdapter(adapter)
    }
    fun setSendMessage(sendMessage: SendMessage) {
        this.sendMessage = sendMessage
    }
    override fun onResume() {
        super.onResume()
        sendMessage!!.setTitle("Order Record")
    }
}
