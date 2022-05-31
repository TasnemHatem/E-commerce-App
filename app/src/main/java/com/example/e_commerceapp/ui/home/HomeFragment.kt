package com.example.e_commerceapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentHomeBinding
import com.example.e_commerceapp.ui.home.model.VendorsResponse
import com.example.e_commerceapp.ui.home.viewmodel.VendorsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

private const val TAG = "HomeFragment"
@AndroidEntryPoint
class HomeFragment :BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private lateinit var   recyclerViewBrand :RecyclerView
    private lateinit var vendorsResponse:VendorsResponse
    private lateinit var   vendorResponseAdapter: VendorResponseAdapter

    private lateinit var   recyclerViewBaner :RecyclerView
    private lateinit var   banerAdapter: BanerAdapter
    val mViewModel:VendorsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         initBrandRecyclerView(view)
        initBanerRecyclerView(view)

    }
    override fun afterOnCreateView() {
        super.afterOnCreateView()

        mViewModel.error.observeInFragment(viewLifecycleOwner){
            //TODO Add logic depending on Exception Type
        }
        mViewModel.vendors.observeInFragment(viewLifecycleOwner){
            Log.e(TAG, "afterOnCreateView: " +it.body())
            when(it.body()){
                null ->{
                    //TODO show to user error
                }
                else ->{
                    when(it.isSuccessful){
                        true -> {
                            vendorResponseAdapter.setListTOAdaptr(it.body()!!)
                            Log.e(TAG, "afterOnCreateView: ${it.body()}")
                        }
                        else->{
                            //TODO check $

                        }
                    }
                }
            }
        }
        mViewModel.requestVendors()

    }

    private fun initBrandRecyclerView(view:View){
        recyclerViewBrand =view.findViewById(R.id.brands_recyclerview)

        val grid =GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        recyclerViewBrand.setLayoutManager(grid)

        vendorsResponse = VendorsResponse(ArrayList())
        vendorResponseAdapter = VendorResponseAdapter( vendorsResponse)
        recyclerViewBrand.setAdapter(vendorResponseAdapter)
    }

    private fun initBanerRecyclerView(view:View){

        recyclerViewBaner =view.findViewById(R.id.advertise_recyclerview)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        recyclerViewBaner.setLayoutManager(layoutManager)
       var banerList = listOf(
             R.drawable.banner1,
          R.drawable.banner2,
           R.drawable.banner3,)
        banerAdapter = BanerAdapter(banerList)
        recyclerViewBaner.setAdapter(banerAdapter)
    }
}