package com.ytowka.mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ytowka.mvvm.R
import com.ytowka.mvvm.model.Named
import com.ytowka.mvvm.view.MainActivity.Companion.int
import com.ytowka.mvvm.viewmodel.NodViewModel
import kotlinx.android.synthetic.main.fragment_nn.*

class NodFragment(override var name: String) : Fragment(), Named{
    constructor() : this("dum")
    lateinit var viewModel: NodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_nn, container, false)
    }

    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(this).get(NodViewModel::class.java)
        viewModel.result.observe(this){
            if(it != null){
                val (a,b) = it
                lcmgcdText.text = "${getString(R.string.GCD)} = $a \n${getString(R.string.LCM)} = $b"
            }
        }
        calcnod.setOnClickListener {
            val a = if (numA.int() != 0) numA.int() else 1
            val b = if (numB.int() != 0) numB.int() else 1
            viewModel.calculate(a,b)
        }
    }
}