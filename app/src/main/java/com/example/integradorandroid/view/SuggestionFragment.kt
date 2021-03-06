package com.example.integradorandroid.view

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.integradorandroid.R
import com.example.integradorandroid.databinding.SuggestionLayoutBinding
import com.example.integradorandroid.network.BoredResponse
import com.example.integradorandroid.network.ResponseApi
import com.example.integradorandroid.utils.Categories
import com.example.integradorandroid.utils.Constants
import com.example.integradorandroid.utils.Prices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuggestionFragment: Fragment() {

    private lateinit var mBinding: SuggestionLayoutBinding
    private var participants = ""
    private var category = ""

    private val args : SuggestionFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = SuggestionLayoutBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        config()

    }

    private fun config(){
        participants = args.participantsCount.toString()
        category = args.selectedActivity.toString()

        when(category){
            "" -> searchRandomActivities(category)
            else -> searchActivitiesByType(category,participants)
        }

        mBinding.ButtonTryAnother.setOnClickListener {
            searchActivitiesByType(category, participants)
        }
    }

    private fun searchActivitiesByType(type: String, participants: String){

        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit(Constants.BASE_URL)
                .create(ResponseApi::class.java)
                .getActivityByType(Constants.END_POINT_TYPE + type + Constants.END_POINT_PARTICIPANTS + participants)

            val boredResponse = call.body()

            activity?.runOnUiThread {
                if(call.isSuccessful){
                    if(!boredResponse?.activity.isNullOrEmpty()){
                        boredResponse?.let {
                            loadActivityData(it)
                        }
                    }else{
                        showError()
                    }
                }else{
                    showError()
                }
            }
        }
    }

    private fun searchRandomActivities(participants: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit(Constants.BASE_URL)
                .create(ResponseApi::class.java)
                .getActivityByType(Constants.END_POINT_RANDOM + participants) //Suggestions: call function getActivityByRandom

            val boredResponse= call.body()

            activity?.runOnUiThread {
                if(call.isSuccessful){
                    if(!boredResponse?.activity.isNullOrEmpty()){
                        boredResponse?.let {
                            loadActivityData(it)
                        }
                    }else{
                        showError()
                    }
                }else{
                    showError()
                }
            }
        }
    }

    private fun showError(){
        mBinding.apply {
            ErrorConstraintLayout.visibility = View.VISIBLE
            SuccessConstraintLayout.visibility = View.GONE
        }
    }

    private fun loadActivityData(boredResponse: BoredResponse){
        mBinding.apply {
            boredResponse?.let {
                ErrorConstraintLayout.visibility = View.GONE
                SuccessConstraintLayout.visibility = View.VISIBLE
                when (it.price) {
                    0.0 -> TextViewPrice.text = Prices.Free.name
                    in 0.0..0.3 -> TextViewPrice.text = Prices.Low.name
                    in 0.3..0.6 -> TextViewPrice.text = Prices.Medium .name
                    in 0.6..1.0 -> TextViewPrice.text = Prices.High .name
                }

                TextViewActivityTitle.text = boredResponse.activity
                TextViewParticipants.text = boredResponse.participants.toString()
            }

            ButtonTryAnother.setOnClickListener {
                when(category){
                    "" -> searchRandomActivities(category) //Suggestions: enter participants
                    else -> searchActivitiesByType(category,participants)
                }
            }
        }
    }

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}