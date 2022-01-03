package com.example.integradorandroid.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.integradorandroid.R
import com.example.integradorandroid.databinding.InitialLayoutBinding
import com.example.integradorandroid.utils.disableButton
import com.example.integradorandroid.utils.enableButton

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
                if(checkBox.isChecked){
                    val action = InitialFragmentDirections.actionInitialFragmentToActivityListFragment()
                    action.participantsCount = EditTextParticipants.text.toString()
                    it.findNavController().navigate(action)
                }else{
                    Toast.makeText(context, getString(R.string.accept_term_and_conditions), Toast.LENGTH_LONG).show()
                }
            }
            TextViewTermsAndConditions.setOnClickListener{
                val action = InitialFragmentDirections.actionInitialFragmentToTermsAndConditionFragments()
                it.findNavController().navigate(action)
            }

            EditTextParticipants.doAfterTextChanged {
                when{
                    it.toString().isNullOrEmpty() -> mBinding.ButtonStart.disableButton()
                    it.toString().isBlank() -> mBinding.ButtonStart.disableButton()
                    it.toString().toInt() < 1 -> mBinding.ButtonStart.disableButton()
                    else -> mBinding.ButtonStart.enableButton()
                }
            }

        }

    }
}