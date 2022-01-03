package com.example.integradorandroid.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.integradorandroid.databinding.InitialLayoutBinding
import com.example.integradorandroid.utils.disableButton

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
        mBinding?.apply {
            ButtonStart.setOnClickListener {
                if(valueControl()){
                    val action = InitialFragmentDirections.actionInitialFragmentToActivityListFragment()
                    action.participantsCount = EditTextParticipants.text.toString()
                    it.findNavController().navigate(action)
                }
            }
            TextViewTermsAndConditions.setOnClickListener{
                val action = InitialFragmentDirections.actionInitialFragmentToTermsAndConditionFragments()
                it.findNavController().navigate(action)
            }

        }

    }

    private fun valueControl(): Boolean{
        when{
            mBinding.EditTextParticipants.text.toString().isNullOrEmpty() -> mBinding.ButtonStart.isEnabled = true
            mBinding.EditTextParticipants.text.toString().isBlank() -> mBinding.ButtonStart.isEnabled = true
            mBinding.EditTextParticipants.text.toString().toInt() < 1 -> mBinding.ButtonStart.isEnabled = true
            else -> {
                mBinding.ButtonStart.isEnabled = false
                return true
            }
        }
        mBinding.ButtonStart.disableButton()
        return false
    }

}