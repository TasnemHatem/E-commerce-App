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
import com.example.e_commerceapp.ui.order.model.Order
import com.example.e_commerceapp.ui.order.viewmodel.OrderVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : BaseFragment<FragmentOrdersBinding>(FragmentOrdersBinding::inflate){

    private lateinit var ordersResponse: List<Order>
    lateinit var orderAdapter: OrderAdapter

    val viewmodel: OrderVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ordersResponse = listOf<Order>()
        // = WishListAdapter(requireContext(), wishlistResponse, this)
        orderAdapter = OrderAdapter(requireContext(), ordersResponse)
        binding.ordersRecycleViewId.adapter = orderAdapter

        var layoutManager: RecyclerView.LayoutManager =  LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        binding.ordersRecycleViewId.layoutManager = layoutManager
    }
    override fun afterOnCreateView() {
        super.afterOnCreateView()

        viewmodel.error.observeInFragment(viewLifecycleOwner){
            //TODO Add logic depending on Exception Type
        }
        viewmodel.orderlist.observeInFragment(viewLifecycleOwner){
            Log.i("EMYTAG", "afterOnCreateView: Ya Orders ${it} ")
            Log.i("EMYTAG", "afterOnCreateView: Ya Orders Size ${it.size}")
            ordersResponse = it
            orderAdapter.data = ordersResponse
            orderAdapter.notifyDataSetChanged()
        }
        viewmodel.requestOrders()

    }

}