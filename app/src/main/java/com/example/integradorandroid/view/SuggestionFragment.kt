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

        searchActivitiesByType(category,participants)

        mBinding.ButtonTryAnother.setOnClickListener {
            searchActivitiesByType(category, participants)
        }
    }

    private fun searchActivitiesByType(type: String, participants: String){

        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit(Constants.BASE_URL)
                .create(ResponseApi::class.java)
                .getActivityByType(Constants.END_POINT_TYPE + type + "&participants=$participants")

            val boredResponse = call.body()

            activity?.runOnUiThread {
                if(call.isSuccessful){
                    boredResponse?.let {
                        loadActivityData(it)
                    }
                }else{
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun searchRandomActivities(participants: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit(Constants.BASE_URL)
                .create(ResponseApi::class.java)
                .getActivityByType(Constants.END_POINT_RANDOM + participants)

            val boredResponse= call.body()

            activity?.runOnUiThread {
                if(call.isSuccessful){
                    boredResponse?.let {
                        loadActivityData(it)
                    }
                }else{
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun loadActivityData(boredResponse: BoredResponse){
        mBinding.apply {
            boredResponse?.let {
                when (it.price) {
                    0.0 -> TextViewPrice.text = Prices.Free.name
                    in 0.0..0.3 -> TextViewPrice.text = Prices.Low.name
                    in 0.3..0.6 -> TextViewPrice.text = Prices.Medium .name
                    in 0.6..1.0 -> TextViewPrice.text = Prices.High .name
                }

                TextViewActivityTitle.text = boredResponse.activity
                TextViewParticipants.text = boredResponse.participants.toString()
            }
        }
    }

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}