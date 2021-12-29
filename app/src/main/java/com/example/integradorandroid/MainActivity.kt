package com.example.integradorandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.integradorandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mNavController: NavController
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mNavController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, mNavController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(mNavController, null)
    }
}