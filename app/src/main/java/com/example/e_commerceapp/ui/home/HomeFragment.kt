package com.example.e_commerceapp.ui.home

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentHomeBinding
import com.example.e_commerceapp.ui.home.repo.VendorsRepoImpl
import com.example.e_commerceapp.ui.home.viewmodel.VendorsViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "HomeFragment"
@AndroidEntryPoint
class HomeFragment :BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    val mViewModel:VendorsViewModel by viewModels()
    override fun afterOnCreateView() {
        super.afterOnCreateView()
        mViewModel.error.observeInFragment(viewLifecycleOwner){
            //TODO Add logic depending on Exception Type
        }
        mViewModel.vendors.observeInFragment(viewLifecycleOwner){
            when(it.body()){
                null ->{
                    //TODO show to user error
                }
                else ->{
                    when(it.isSuccessful){
                        true -> {
                            Log.e(TAG, "afterOnCreateView: ${it.body()}")
                        }
                        else->{
                            //TODO check $
//                            it.code()
//                            it.message()
                        }
                    }
                }
            }
        }
        mViewModel.requestVendors()

    }
}