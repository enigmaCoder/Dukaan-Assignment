package com.example.dukaan.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Products Feature Coming Soon..."
    }
    val text: LiveData<String> = _text
}