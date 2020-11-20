package com.adharul.i.moviecatalogue.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adharul.i.moviecatalogue.R
import com.adharul.i.moviecatalogue.view.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mFragmentManager = supportFragmentManager
        val mHomeFragment = HomeFragment()
        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)
        if (fragment !is HomeFragment) {
            mFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, mHomeFragment, HomeFragment::class.java.simpleName)
                .addToBackStack(null)
                .commit()
        }
    }
}