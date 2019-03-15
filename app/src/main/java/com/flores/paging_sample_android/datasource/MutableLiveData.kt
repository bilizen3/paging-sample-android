package com.flores.paging_sample_android.datasource

import androidx.lifecycle.LiveData

class MutableLiveData<T> : LiveData<T>() {
    override fun postValue(value: T) {
        super.postValue(value)
    }

    override fun setValue(value: T) {
        super.setValue(value)
    }
}