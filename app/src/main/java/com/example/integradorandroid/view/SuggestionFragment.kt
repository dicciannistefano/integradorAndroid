package com.example.integradorandroid.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.integradorandroid.databinding.SuggestionLayoutBinding
import com.example.integradorandroid.network.BoredResponse
import com.example.integradorandroid.network.ResponseApi
import com.example.integradorandroid.utils.Categories
import com.example.integradorandroid.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuggestionFragment: Fragment() {

    private lateinit var mBinding: SuggestionLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = SuggestionLayoutBinding.inflate(inflater, container, false)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchRandomActivities()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun searchActivitiesByType(type: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit()
                .create(ResponseApi::class.java)
                .getActivityByType(Categories.Music.name)

            val boredResponse: BoredResponse? = call.body()

            activity?.runOnUiThread {
                if(call.isSuccessful){
                    mBinding.apply {
                        TextViewActivityTitle.text = boredResponse?.activity
                    }
                }else{
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG)
                }
            }
        }
    }

    private fun searchRandomActivities(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit()
                .create(ResponseApi::class.java)
                .getRandomActivity("")

            val boredResponse: BoredResponse? = call.body()

            activity?.runOnUiThread {
                if(call.isSuccessful){
                    mBinding.apply {
                        TextViewActivityTitle.text = boredResponse?.activity
                    }
                }else{
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG)
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}