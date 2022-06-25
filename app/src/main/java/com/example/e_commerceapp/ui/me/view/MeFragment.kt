package com.example.e_commerceapp.ui.me.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.network.DataState
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentMeBinding
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.cart.viewmodel.CartViewModel
import com.example.e_commerceapp.ui.order.model.Order
import com.example.e_commerceapp.ui.order.view.OrderAdapter
import com.example.e_commerceapp.ui.order.viewmodel.OrderVM
import com.example.e_commerceapp.ui.wishlist.model.LineItem
import com.example.e_commerceapp.ui.wishlist.model.toListItem
import com.example.e_commerceapp.ui.wishlist.view.OnWishlistClickListenert
import com.example.e_commerceapp.ui.wishlist.view.WishListAdapter
import com.example.e_commerceapp.ui.wishlist.viewmodel.WishlistVM
import com.example.e_commerceapp.utils.Constants.SHARED_NAME
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.text.Typography.dagger

@AndroidEntryPoint
class MeFragment : BaseFragment<FragmentMeBinding>(FragmentMeBinding::inflate), OnWishlistClickListenert {
    //orders
    private lateinit var ordersResponse: List<Order>
    lateinit var orderSubAdapter: OrderSubAdapter
    val ordersViewmodel: OrderVM by viewModels()
    val cartViewModel: CartViewModel by viewModels()

    //wishlist
    private lateinit var wishlistResponse: List<LineItem>
    lateinit var wishlistSubAdapter: WishlistSubAdapter
    val wishlistViewmodel: WishlistVM by viewModels()

    @Inject
    lateinit var appSharedPreference: AppSharedPreference
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userNameId.text = "Hi, " + appSharedPreference.getStringValue(SHARED_NAME, "")

        if (!appSharedPreference.getBooleanValue("login")) {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_mainFragment_to_loginFragment)
        }

        binding.viewallOrdersBtnId.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_mainFragment_to_orderFragment)
        }

        binding.viewallWishlistBtnId.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_mainFragment_to_wishlistFragment)
        }

        setupOrders()
        setupWishlist(this)
    }

    fun setupOrders(){
        ordersResponse = listOf<Order>()
        orderSubAdapter = OrderSubAdapter(requireContext(), ordersResponse, appSharedPreference)
        binding.orderRecycleViewId.adapter = orderSubAdapter

        var layoutManager: RecyclerView.LayoutManager =  LinearLayoutManager(view?.context, RecyclerView.VERTICAL, false)
        binding.orderRecycleViewId.layoutManager = layoutManager
        cartViewModel.addItemResponse.observeInFragment(viewLifecycleOwner){
            when(it){
                is DataState.Success -> Toast.makeText(context, context?.resources?.getString(R.string.added_successfully_to_cart), Toast.LENGTH_SHORT).show()
                is DataState.Error -> Toast.makeText(context, context?.resources?.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        }
        ordersViewmodel.error.observeInFragment(viewLifecycleOwner){

        }
        ordersViewmodel.orderlist.observeInFragment(viewLifecycleOwner){
            if(it.isNullOrEmpty()){
                binding.progressBarOrdersId.visibility = View.GONE
                binding.ordersEmptyMsgId.visibility = View.VISIBLE
            }else{
                binding.progressBarOrdersId.visibility = View.GONE
                binding.ordersEmptyMsgId.visibility = View.GONE
            }
            ordersResponse = it
            orderSubAdapter.data = ordersResponse
            orderSubAdapter.notifyDataSetChanged()
        }
        binding.progressBarOrdersId.visibility = View.VISIBLE
        ordersViewmodel.requestOrders()
    }

    fun setupWishlist(listener: OnWishlistClickListenert){
        wishlistResponse = listOf<LineItem>()
        wishlistSubAdapter = WishlistSubAdapter(requireContext(), wishlistResponse, listener, appSharedPreference)
        binding.wishlistRecycleViewId.adapter = wishlistSubAdapter

        var layoutManager: RecyclerView.LayoutManager = GridLayoutManager(view?.context, 2, )
        binding.wishlistRecycleViewId.layoutManager = layoutManager

        wishlistViewmodel.error.observeInFragment(viewLifecycleOwner){
            //TODO Add logic depending on Exception Type
        }
        wishlistViewmodel.wishlist.observeInFragment(viewLifecycleOwner){
            if(it.isNullOrEmpty() || it.size == 1){
                binding.progressBarWishlistId.visibility = View.GONE
                binding.wishlistEmptyMsgId.visibility = View.VISIBLE
            }else{
                binding.progressBarWishlistId.visibility = View.GONE
                binding.wishlistEmptyMsgId.visibility = View.GONE
            }
            wishlistResponse = it
            wishlistSubAdapter.data = wishlistResponse
            wishlistSubAdapter.notifyDataSetChanged()
        }
        wishlistViewmodel.requestWishlist()
    }

    override fun clickDeleteListener(deletedItem: LineItem) {
        wishlistViewmodel.deleteWish(deletedItem)
    }

    override fun clickAddToCartListener(item: LineItem) {
        cartViewModel.addItem(item.toListItem())
    }

}