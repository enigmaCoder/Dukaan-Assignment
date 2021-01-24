package com.example.dukaan.ui.marketing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dukaan.R

class MarketingFragment : Fragment() {

    private lateinit var marketingViewModel: MarketingViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        marketingViewModel =
                ViewModelProviders.of(this).get(MarketingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_marketing, container, false)
        val textView: TextView = root.findViewById(R.id.text_marketing)
        marketingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}