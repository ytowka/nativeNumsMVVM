package com.ytowka.mvvm.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputEditText
import com.ytowka.mvvm.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){
    companion object{
        fun printd(massege: String){
            Log.i("debug",massege)
        }
        fun TextInputEditText.int(): Int {
            when (text.toString()) {
                "" -> return 0
                else -> return text.toString().toInt()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = listOf(PrimeNumFragment(resources.getString(R.string.primeNums)), NodFragment(resources.getString(R.string.NN)))

        viewPager.adapter = TabAdapter(this, list)
        TabLayoutMediator(tabLayout, viewPager){ tab, pos ->
            tab.text = list[pos].name
        }.attach()
    }
}

