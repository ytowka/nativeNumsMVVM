package com.ytowka.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ytowka.mvvm.model.LcmCalc
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NodViewModel : ViewModel() {
    val result = MutableLiveData<LcmCalc.Answer?>()
    val calculator = LcmCalc()
    fun calculate(a: Int, b: Int){
        result.value = null
        CoroutineScope(Dispatchers.Default).launch {
            result.postValue(calculator.calculate(a,b))
        }
    }
}