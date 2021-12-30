package com.example.integradorandroid.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.integradorandroid.R
import com.example.integradorandroid.databinding.InitialLayoutBinding

class InitialFragment: Fragment() {

    private lateinit var mBinding: InitialLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = InitialLayoutBinding.inflate(inflater, container, false)

        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.ButtonStart.setOnClickListener {
                val action = InitialFragmentDirections.actionInitialFragmentToActivityListFragment()
                it.findNavController().navigate(action)
            }
        mBinding.TextViewTermsAndConditions.setOnClickListener{
                val action = InitialFragmentDirections.actionInitialFragmentToTermsAndConditionFragments()
                it.findNavController().navigate(action)
            }
        }



    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }
}