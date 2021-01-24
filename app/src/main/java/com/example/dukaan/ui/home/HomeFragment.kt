package com.example.dukaan.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dukaan.R
import kotlinx.android.synthetic.main.floating_layout.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.initialize(this.requireContext())
        val parentRecyclerView = main_recycler as RecyclerView
        parentRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        homeViewModel.sampleOrderData.headersData?.let {
            parentRecyclerView.adapter = HeadersAdapter(it,this.requireContext())
        }
    }
}