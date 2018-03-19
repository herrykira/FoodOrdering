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
import com.dant.centersnapreyclerview.SnappingRecyclerView
import com.example.kinhangpoon.foodordering.R
import com.example.kinhangpoon.foodordering.utility.MyAdapter
import com.example.kinhangpoon.foodordering.utility.MyPlacesAdapter
import com.example.kinhangpoon.foodordering.utility.SendMessage

/**
 * Created by hefen on 3/18/2018.
 */
class PlacesFragment: Fragment(), MyPlacesAdapter.ItemModifier {
    internal var sendMessage: SendMessage? = null
    internal var rootView: View? = null
    internal var context: Context? = null
    internal var recyclerViewItems: RecyclerView? = null
    internal var adapter: MyPlacesAdapter? = null

    override fun onItemSelected(position: Int) {
        //Log.i("mylog", "item " + position)
        sendMessage!!.sendData(position)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        rootView = inflater!!.inflate(R.layout.fragment_places, container, false)
        //Log.i("mylog", "on create fragment");
        initPlaces()

        return rootView
    }

    fun initPlaces() {
        context = rootView!!.context

        recyclerViewItems = rootView!!.findViewById(R.id.recyclerView2)
        recyclerViewItems!!.setLayoutManager(StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL))
        recyclerViewItems!!.setHasFixedSize(true)

        adapter = MyPlacesAdapter()
        adapter!!.setItemModifier(this)//??this context
        recyclerViewItems!!.setAdapter(adapter)
    }

    fun setSendMessage(sendMessage: SendMessage) {
        this.sendMessage = sendMessage
    }
    override fun onResume() {
        super.onResume()
        sendMessage!!.setTitle("Get Your Local Menu")
    }
}