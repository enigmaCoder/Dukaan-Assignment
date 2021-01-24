package com.example.dukaan.ui.home

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dukaan.R
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Feature Making..."
    }
    val text: LiveData<String> = _text
    val JSON_CONFIGURATION_STRICT_MODE: ((strictMode: Boolean) -> Json) = { strictMode ->  Json { isLenient = !strictMode ; allowSpecialFloatingPointValues = !strictMode ; ignoreUnknownKeys = !strictMode ;useArrayPolymorphism = !strictMode } }

    lateinit var sampleOrderData: OrderSampleData

    fun initialize(context: Context){
        val jsonString = readRawResourceFile(R.raw.sample_data_dukaan, context)
        sampleOrderData = JSON_CONFIGURATION_STRICT_MODE(false).decodeFromString(
            OrderSampleData.serializer(),
            jsonString
        )
    }

    private fun readRawResourceFile(resourceid: Int, context: Context): String {
        val strReturn = StringBuilder()
        try {
            val fIn = context.resources.openRawResource(resourceid)
            val file = InputStreamReader(fIn)
            val br = BufferedReader(file)
            var line = br.readLine ()

            while (line!= null) {
                strReturn.append(line + "\n")
                line = br.readLine ()
            }
            br.close ()
            file.close ()

        } catch (e: IOException) {
            println(e.stackTrace)
        }

        return strReturn.toString()

    }

}