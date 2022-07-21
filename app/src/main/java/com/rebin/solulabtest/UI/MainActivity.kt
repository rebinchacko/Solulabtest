package com.rebin.solulabtest.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.rebin.solulabtest.R
import com.rebin.solulabtest.UI.fragments.Homefragment

class MainActivity : AppCompatActivity() {


    lateinit var homefragment: Homefragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView(Homefragment())

    }

    private fun initView(fragment: Fragment) {
        val fm: FragmentManager = getSupportFragmentManager()
        for (i in 0 until fm.getBackStackEntryCount()) {
            fm.popBackStack()
        }
        FragmentUtils.replaceFragment(
            this,
            fragment,
            R.id.fmLytContainer,
            false
        )

    }

}