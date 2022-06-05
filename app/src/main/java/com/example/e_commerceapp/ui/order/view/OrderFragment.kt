package com.example.e_commerceapp.ui.order.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentOrdersBinding

class OrderFragment : BaseFragment<FragmentOrdersBinding>(FragmentOrdersBinding::inflate){

    lateinit var orderAdapter: OrderAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderAdapter = OrderAdapter(requireContext())
        binding.ordersRecycleViewId.adapter = orderAdapter

        var layoutManager: RecyclerView.LayoutManager =  LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        binding.ordersRecycleViewId.layoutManager = layoutManager
    }
}