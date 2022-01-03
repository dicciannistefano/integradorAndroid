package com.example.integradorandroid.recyclerview

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.integradorandroid.databinding.ActivityItemBinding
import com.example.integradorandroid.utils.Categories
import com.example.integradorandroid.view.ActivityListFragmentDirections
import com.example.integradorandroid.view.InitialFragmentDirections

class ActivityViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val binding = ActivityItemBinding.bind(view)
    private var participantQuantity = ""

    fun setParticipantsQuantity(participantsQuantityValue: String){
        participantQuantity = participantsQuantityValue
    }

    fun bind(activityAtPosition : Categories){
        binding.apply {
            activityTextView.text = activityAtPosition.name
            LinearLayoutItem.setOnClickListener{
                val action = ActivityListFragmentDirections.actionActivityListFragmentToSuggestionFragment()
                action.participantsCount = participantQuantity
                action.selectedActivity = activityAtPosition.name
                it.findNavController().navigate(action)
            }
        }
    }

}