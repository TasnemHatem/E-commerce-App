package com.example.e_commerceapp.ui.wishlist.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.network.DataState
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentWishlistBinding
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.cart.viewmodel.CartViewModel
import com.example.e_commerceapp.ui.wishlist.model.Customer
import com.example.e_commerceapp.ui.wishlist.model.DraftOrder
import com.example.e_commerceapp.ui.wishlist.model.LineItem
import com.example.e_commerceapp.ui.wishlist.model.toListItem
import com.example.e_commerceapp.ui.wishlist.viewmodel.WishlistVM
import com.example.e_commerceapp.utils.ConnectionLiveData
import com.example.e_commerceapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WishlistFragment : BaseFragment<FragmentWishlistBinding>(FragmentWishlistBinding::inflate), OnWishlistClickListenert{

    @Inject
    lateinit var appSharedPreference: AppSharedPreference

    private lateinit var wishlistResponse: List<LineItem>
    lateinit var wishlistAdapter: WishListAdapter

    val viewmodel: WishlistVM by viewModels()
    val cartViewModel: CartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listenerToNetwork()

        wishlistResponse = listOf<LineItem>()
        wishlistAdapter = WishListAdapter(requireContext(), wishlistResponse, this)
        binding.wishlistRecycleViewId.adapter = wishlistAdapter

        var layoutManager: RecyclerView.LayoutManager = GridLayoutManager(view.context, 2, )
        binding.wishlistRecycleViewId.layoutManager = layoutManager

        binding.btnBack.setOnClickListener {
            navController.navigateUp()
        }
    }
    override fun afterOnCreateView() {
        super.afterOnCreateView()
        cartViewModel.addItemResponse.observeInFragment(viewLifecycleOwner){
            when(it){
                is DataState.Success -> Toast.makeText(context, context?.resources?.getString(R.string.added_successfully_to_cart), Toast.LENGTH_SHORT).show()
                is DataState.Error -> Toast.makeText(context, context?.resources?.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        }
        viewmodel.error.observeInFragment(viewLifecycleOwner){
            //TODO Add logic depending on Exception Type
        }
        viewmodel.wishlist.observeInFragment(viewLifecycleOwner){
            if(it.isNullOrEmpty() || it.size == 1){
                binding.progressBarWishlistId.visibility = View.GONE
                binding.emptyMsgId.visibility = View.VISIBLE
            }else{
                binding.progressBarWishlistId.visibility = View.GONE
                binding.emptyMsgId.visibility = View.INVISIBLE
            }
            wishlistResponse = it
            wishlistAdapter.data = wishlistResponse
            wishlistAdapter.notifyDataSetChanged()
        }
        viewmodel.requestWishlist()

    }


    override fun clickDeleteListener(deletedItem: LineItem) {
        viewmodel.deleteWish(deletedItem)
    }

    override fun clickAddToCartListener(item: LineItem) {
        cartViewModel.addItem(item.toListItem())
    }

    private fun listenerToNetwork() {
        ConnectionLiveData(requireContext()).observe(viewLifecycleOwner) {
            if (it) {
                binding.wishlistRecycleViewId.visibility = View.VISIBLE
                binding.imageView.visibility = View.GONE
                binding.progressBarWishlistId.visibility = View.VISIBLE
                viewmodel.requestWishlist()
            } else {
                binding.wishlistRecycleViewId.visibility = View.GONE
                binding.progressBarWishlistId.visibility = View.GONE
                binding.emptyMsgId.visibility = View.INVISIBLE
                binding.imageView.visibility = View.VISIBLE
            }
        }
    }
}