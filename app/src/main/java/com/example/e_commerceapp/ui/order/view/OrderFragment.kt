package com.example.e_commerceapp.ui.order.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentOrdersBinding
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.order.model.Order
import com.example.e_commerceapp.ui.order.viewmodel.OrderVM
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OrderFragment : BaseFragment<FragmentOrdersBinding>(FragmentOrdersBinding::inflate){

    private lateinit var ordersResponse: List<Order>
    lateinit var orderAdapter: OrderAdapter

    val viewmodel: OrderVM by viewModels()

    @Inject
    lateinit var appSharedPreference: AppSharedPreference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ordersResponse = listOf<Order>()
        orderAdapter = OrderAdapter(requireContext(), ordersResponse)
        binding.ordersRecycleViewId.adapter = orderAdapter

        var layoutManager: RecyclerView.LayoutManager =  LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        binding.ordersRecycleViewId.layoutManager = layoutManager

        binding.btnBack.setOnClickListener {
            navController.navigateUp()
        }
    }
    override fun afterOnCreateView() {
        super.afterOnCreateView()

        viewmodel.error.observeInFragment(viewLifecycleOwner){

        }
        viewmodel.orderlist.observeInFragment(viewLifecycleOwner){
            if(it.isNullOrEmpty()){
                binding.progressBarOrdersId.visibility = View.GONE
                binding.emptyMsgId.visibility = View.VISIBLE
            }else{
                binding.progressBarOrdersId.visibility = View.GONE
                binding.emptyMsgId.visibility = View.INVISIBLE
            }
            ordersResponse = it
            orderAdapter.data = ordersResponse
            orderAdapter.notifyDataSetChanged()
        }
        binding.progressBarOrdersId.visibility = View.VISIBLE
        viewmodel.requestOrders()

    }

}