package com.example.integradorandroid.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
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
        searchActivitiesByType(Categories.music.name)

        mBinding.ButtonTryAnother.setOnClickListener {
            searchActivitiesByType(Categories.music.name)
        }
    }

    private fun searchActivitiesByType(type: String){
        CoroutineScope(Dispatchers.IO).launch {
            var boredResponse: BoredResponse?
            var call: Response<BoredResponse>?
            do{
                call = getRetrofit(Constants.BASE_URL_TYPE)
                    .create(ResponseApi::class.java)
                    .getActivityByType(Constants.BASE_URL_TYPE2 + type)

                boredResponse= call.body()
            } while (boredResponse?.participants != 4)

            activity?.runOnUiThread {
                call?.let {
                    if(call.isSuccessful){
                        loadActivityData(boredResponse)
                    }else{
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun searchRandomActivities(){
        CoroutineScope(Dispatchers.IO).launch {
            var boredResponse: BoredResponse?
            var call: Response<BoredResponse>?
            do{
                call = getRetrofit(Constants.BASE_URL_RANDOM)
                    .create(ResponseApi::class.java)
                    .getActivityByType("")

                boredResponse= call.body()
            } while (boredResponse?.participants != 4)

            activity?.runOnUiThread {
                call?.let {
                    if(call.isSuccessful){
                        loadActivityData(boredResponse)
                    }else{
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    }
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