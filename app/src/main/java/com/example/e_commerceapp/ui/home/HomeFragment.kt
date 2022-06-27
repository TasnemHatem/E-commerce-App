package com.example.e_commerceapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentHomeBinding
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.home.model.VendorsResponse
import com.example.e_commerceapp.ui.home.viewmodel.VendorsViewModel
import com.example.e_commerceapp.utils.Constants
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.collections.ArrayList

private const val TAG = "HomeFragment"
@AndroidEntryPoint
class HomeFragment :BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate), OnBrandClickListener {

    private lateinit var   vendorResponseAdapter: VendorResponseAdapter
    val mViewModel:VendorsViewModel by viewModels()

    @Inject
    lateinit var appSharedPreference: AppSharedPreference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         initBrandRecyclerView(view)
        initSlider()
        // initBanerRecyclerView(view)

        if (!appSharedPreference.getBooleanValue("login")) {
            appSharedPreference.setValue(Constants.SHARED_CURRENCY_CODE, "EGP")
            appSharedPreference.setValue(Constants.SHARED_CURRENCY_VALUE, 18.78)
        }
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

        val grid =GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
      var  vendorsResponse = VendorsResponse(ArrayList())
        vendorResponseAdapter = VendorResponseAdapter( vendorsResponse, this)
         binding.brandsRecyclerview.apply {
             layoutManager = grid
             adapter=vendorResponseAdapter
         }
    }

//    private fun initBanerRecyclerView(view:View){
///
///
//        val _layoutManager = LinearLayoutManager(context)
//        _layoutManager.orientation = RecyclerView.HORIZONTAL
//        var banerAdapter = BanerAdapter(listOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3))
//        binding.advertiseRecyclerview.apply {
//            layoutManager = _layoutManager
//            adapter= banerAdapter
//        }
//    }

    override fun clickBrandBtn(vendor:String) {
        val bundle = bundleOf("amount" to vendor)
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_mainFragment_to_productFragment2,bundle)
    }

    private fun initSlider(){
        var images = intArrayOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3)
        val _sliderAdapter = SliderAdapter(images)
        binding.imageSlider.setSliderAdapter(_sliderAdapter)
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        binding.imageSlider.startAutoCycle()
    }
}