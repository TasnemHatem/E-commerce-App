package com.example.e_commerceapp.ui.wishlist.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentWishlistBinding
import com.example.e_commerceapp.ui.wishlist.model.WishlistResponse
import com.example.e_commerceapp.ui.wishlist.viewmodel.WishlistVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishlistFragment : BaseFragment<FragmentWishlistBinding>(FragmentWishlistBinding::inflate){

    private lateinit var wishlistResponse: WishlistResponse

    val viewmodel: WishlistVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun afterOnCreateView() {
        super.afterOnCreateView()

        viewmodel.error.observeInFragment(viewLifecycleOwner){
            //TODO Add logic depending on Exception Type
        }
        viewmodel.wishlist.observeInFragment(viewLifecycleOwner){
            Log.e("EMYTAG", "afterOnCreateView: " +it.body())
            when(it.body()){
                null ->{
                    //TODO show to user error
                }
                else ->{
                    when(it.isSuccessful){
                        true -> {
                            Log.i("EMYTAG", "afterOnCreateView: Data + ${it.body()}")
                        }
                        else->{
                            //TODO check $

                        }
                    }
                }
            }
        }
        viewmodel.requestWishlist()

    }
}