package com.example.e_commerceapp.ui.wishlist.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentWishlistBinding
import com.example.e_commerceapp.ui.wishlist.model.Customer
import com.example.e_commerceapp.ui.wishlist.model.DraftOrder
import com.example.e_commerceapp.ui.wishlist.model.LineItem
import com.example.e_commerceapp.ui.wishlist.viewmodel.WishlistVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishlistFragment : BaseFragment<FragmentWishlistBinding>(FragmentWishlistBinding::inflate), OnWishlistClickListenert{

    private lateinit var wishlistResponse: List<LineItem>
    lateinit var wishlistAdapter: WishListAdapter

    val viewmodel: WishlistVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wishlistResponse = listOf<LineItem>()
        wishlistAdapter = WishListAdapter(requireContext(), wishlistResponse, this)
        binding.wishlistRecycleViewId.adapter = wishlistAdapter

        var layoutManager: RecyclerView.LayoutManager = GridLayoutManager(view.context, 2, )
        binding.wishlistRecycleViewId.layoutManager = layoutManager
    }
    override fun afterOnCreateView() {
        super.afterOnCreateView()

        viewmodel.error.observeInFragment(viewLifecycleOwner){
            //TODO Add logic depending on Exception Type
        }
        viewmodel.wishlist.observeInFragment(viewLifecycleOwner){
            Log.i("EMYTAG", "afterOnCreateView: Ya Data + ${it}")
            wishlistResponse = it
            wishlistAdapter.data = wishlistResponse
            wishlistAdapter.notifyDataSetChanged()
        }
        viewmodel.requestWishlist()

    }

    override fun clickDeleteListener(id: Long) {

    }
}