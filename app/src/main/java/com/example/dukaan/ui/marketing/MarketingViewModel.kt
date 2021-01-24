package com.example.dukaan.ui.marketing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MarketingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Marketing Feature Coming Soon..."
    }
    val text: LiveData<String> = _text
}