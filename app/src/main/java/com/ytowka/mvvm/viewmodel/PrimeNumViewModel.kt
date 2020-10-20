package com.ytowka.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ytowka.mvvm.model.PrimeNumCalc
import kotlinx.coroutines.*


const val grade = 50
class PrimeNumViewModel() : ViewModel() {
    val result = MutableLiveData<Map<Int,Int>?>()
    val progress = ProgressBarLiveData()
    val calculator = PrimeNumCalc()

    fun calculate(num: Int){
        CoroutineScope(Dispatchers.Default ).launch {
            result.postValue(null)
            launch {
                while (isActive){
                    delay(grade.toLong())
                    withContext(Dispatchers.Main){
                        progress.setProgress(calculator.total,calculator.progress)
                    }
                }
            }
            result.postValue(calculator.calculate(num){
                if (!isActive) {
                    calculator.stop()
                }
            })
        }
    }
    fun stop(){
        calculator.stop()
    }
}