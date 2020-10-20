package com.ytowka.mvvm.viewmodel

import androidx.lifecycle.LiveData

class ProgressBarLiveData : LiveData<ProgressBarLiveData.Progress>() {
    data class Progress(val max: Int, val progress: Int)

    fun setProgress(max: Int, progerss: Int){
        value = Progress(max,progerss)
    }
    fun postProgress(max: Int, progerss: Int){
        postValue(Progress(max,progerss))
    }
    fun getMax(): Int{
        return value?.max ?: 0
    }
    fun getProgress(): Int {
        return value?.progress ?: 0
    }
}