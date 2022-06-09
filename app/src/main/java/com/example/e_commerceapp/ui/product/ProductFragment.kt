package com.example.e_commerceapp.ui.product

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentProductBinding
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.category.model.Product
import com.example.e_commerceapp.ui.category.model.ProductsResponse
import com.example.e_commerceapp.ui.product.viewmodel.ProductViewModel
import com.example.e_commerceapp.ui.wishlist.viewmodel.WishlistVM
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductFragment : BaseFragment<FragmentProductBinding>(FragmentProductBinding::inflate),OnProductClickLisenter {
    @Inject
    lateinit var appSharedPreference: AppSharedPreference

    lateinit var productAdapter:ProductAdapter
    val mViewModel: ProductViewModel by viewModels()
    lateinit var products:ProductsResponse


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProductReyclerView()
        binding.filterProductBtn.setOnClickListener{
            if(!binding.constrainlayout.isVisible) {
                binding.constrainlayout.visibility = View.VISIBLE
            }else{
                binding.constrainlayout.visibility = View.GONE

            }
        }
        binding.productBackBtn.setOnClickListener{
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate( R.id.action_productFragment2_to_mainFragment)

        }
        seekBar()
        mViewModel.vendorsProduct.observeInFragment(viewLifecycleOwner){
            when(it.body()){
                null ->{
                    //TODO show to user error
                }
                else ->{
                    when(it.isSuccessful){
                        true -> {
                            productAdapter.setListTOAdaptr(it.body()!!)
                            products = it.body()!!
                            isVisable(it.body()!!)
                        }
                        else->{
                            //TODO check $

                        }
                    }
                }
            }
        }
        mViewModel.requestVendorsProduct(arguments?.getString("amount").toString())
    }

    private fun initProductReyclerView(){
        val grid = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        var  productsResponse = ProductsResponse(ArrayList())
        productAdapter = ProductAdapter( productsResponse,this,appSharedPreference)
        binding.productRecyclerview.apply {
            layoutManager = grid
                adapter = productAdapter
        }
    }

    fun isVisable( product: ProductsResponse){
        if(product.products.size==0) {
            binding.imagevisabelProduct.visibility = View.VISIBLE
            binding.productRecyclerview.visibility = View.GONE
        }else{
            binding.imagevisabelProduct.visibility = View.GONE
            binding.productRecyclerview.visibility = View.VISIBLE
        }
    }

    fun seekBar(){

        binding.seekBar.progress = 300
        binding.seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed

            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
                binding.endPrice.text= "US$ ${seek.progress}"
               filterByPrice(seek.progress.toDouble())

            }
        })
    }

   private fun filterByPrice(price:Double){
       Log.i("TAG", "filterByPrice: filter price = $price")
       var _product = products.products.filter {s-> s.variants.get(0).price.toDouble() <= price }
       var _productResponse = ProductsResponse(_product)
       productAdapter.setListTOAdaptr(_productResponse)
    }

    override fun viewProductDetailes(product: Product) {
        val bundle = bundleOf("product" to product)
        navController.navigate(R.id.action_productFragment2_to_productDetailes,bundle)

    }

    override fun addTOFavourite(product: Product) {
        Log.i("EMYTAG", "addTOFavourite: ")
        mViewModel.addFavourite(product)
    }

    override fun deletFromFavourite(product: Product) {
        Log.i("EMYTAG", "deletFromFavourite: ")
        mViewModel.deleteFavourite(product)
    }

    override fun goToLogin() {
        navController.navigate(R.id.action_productFragment2_to_loginFragment)
    }


}