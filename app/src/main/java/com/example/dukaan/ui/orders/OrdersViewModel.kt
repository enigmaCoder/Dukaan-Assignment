package com.example.dukaan.ui.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrdersViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Order Feature Coming Soon..."
    }
    val text: LiveData<String> = _text
}