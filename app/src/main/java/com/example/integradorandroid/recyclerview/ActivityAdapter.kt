package com.example.integradorandroid.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.integradorandroid.R
import com.example.integradorandroid.utils.Categories

class ActivityAdapter() : RecyclerView.Adapter<ActivityViewHolder>(){

    private var ActivityList : ArrayList<Categories> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return ActivityViewHolder(layoutInflater.inflate(R.layout.activity_item, parent, false))
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {

        val activityAtPosition = ActivityList[position]
        holder.bind(activityAtPosition)
    }

    override fun getItemCount() = ActivityList.size

    fun addItem(activityList : Array<Categories>){
        ActivityList.addAll(activityList)
    }
}