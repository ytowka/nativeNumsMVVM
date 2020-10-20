package com.ytowka.mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModelProvider
import com.ytowka.mvvm.R
import com.ytowka.mvvm.model.Named
import com.ytowka.mvvm.view.MainActivity.Companion.int
import com.ytowka.mvvm.viewmodel.PrimeNumViewModel
import kotlinx.android.synthetic.main.fragment_native_num.*

class PrimeNumFragment(override var name: String) : Fragment(), Named{
    constructor() : this("dum")
    lateinit var viewModel: PrimeNumViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        avedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_native_num, container, false)
    }

    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(this).get(PrimeNumViewModel::class.java)
        viewModel.result.observe(this){
            if(it != null){
                setData(it)
                loading.visibility = View.GONE
            }
        }
        if(!viewModel.calculator.isCalculating()) loading.visibility = View.GONE
        viewModel.progress.observe(this){
            progressBar.max = it.max
            progressBar.progress = it.progress
        }
        calcButton.setOnClickListener {
            if(!viewModel.calculator.isCalculating()){
                calcButton.setText(R.string.cancel)
                loading.visibility = View.VISIBLE
                leftText.text = ""
                rightText.text = ""
                viewModel.calculate(textEdit.int())
            }else{
                calcButton.setText(R.string.calculate)
                viewModel.stop()
            }
        }
    }
    private fun setData(map: Map<Int,Int>){
        var left = ""
        var right = ""
        map.forEach { (t, u) ->
            left += "$t\n"
            right += "$u\n"
        }
        calcButton.setText(R.string.calculate)
        left += "1"
        leftText.text = left
        rightText.text = right
    }
}