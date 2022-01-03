package com.example.integradorandroid.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.integradorandroid.R
import com.example.integradorandroid.utils.Categories

class ActivityAdapter(participantsQuantity: String) : RecyclerView.Adapter<ActivityViewHolder>(){

    private var activityList : ArrayList<Categories> = arrayListOf()
    private var participantsQuantityValue = participantsQuantity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return ActivityViewHolder(layoutInflater.inflate(R.layout.activity_item, parent, false))
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {

        val activityAtPosition = activityList[position]
        holder.bind(activityAtPosition)
        holder.setParticipantsQuantity(participantsQuantityValue)
    }

    override fun getItemCount() = activityList.size

    fun addItem(newActivityList : Array<Categories>){
        activityList.addAll(newActivityList)
    }
}