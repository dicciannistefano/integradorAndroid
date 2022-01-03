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

    fun bind(activityAtPosition : Categories){
        binding.activityTextView.text = activityAtPosition.name
        binding.LinearLayoutItem.setOnClickListener{
            val action = ActivityListFragmentDirections.actionActivityListFragmentToSuggestionFragment()
            //action.participantsCount = EditTextParticipants.text.toString()

            it.findNavController().navigate(action)
        }
    }

}