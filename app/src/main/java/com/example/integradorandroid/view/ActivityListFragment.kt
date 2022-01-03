package com.example.integradorandroid.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.integradorandroid.R
import com.example.integradorandroid.databinding.ActivitiesLayoutBinding
import com.example.integradorandroid.databinding.InitialLayoutBinding

import com.example.integradorandroid.recyclerview.ActivityAdapter
import com.example.integradorandroid.utils.Categories

class ActivityListFragment : Fragment() {

    private lateinit var mBinding: ActivitiesLayoutBinding
    private lateinit var adapter : ActivityAdapter
    private var categoryList = Categories.values()
    private var participantsQuantity = ""
    private val activityListFragmentsArgs : ActivityListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        mBinding = ActivitiesLayoutBinding.inflate(inflater, container, false)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        participantsQuantity = activityListFragmentsArgs.participantsCount.toString()

        // Init RecyclerView
        initRecyclerView()

        adapter.addItem(categoryList)
        adapter.notifyDataSetChanged()
    }

    private fun initRecyclerView(){

        adapter = ActivityAdapter(participantsQuantity)
        mBinding.RecyclerViewActivities.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_random -> {
                val action = ActivityListFragmentDirections.actionActivityListFragmentToSuggestionFragment()
                action.participantsCount = participantsQuantity
                this.findNavController().navigate(action)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}