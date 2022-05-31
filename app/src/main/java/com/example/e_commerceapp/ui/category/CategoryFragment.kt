package com.example.e_commerceapp.ui.category

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentCategoryBinding
import com.example.e_commerceapp.ui.category.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "CategoryFragment"
@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding> (FragmentCategoryBinding::inflate){
    val mViewModel: CategoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTypeRecycler()

        mViewModel.categoryy.observeInFragment(viewLifecycleOwner){
            Log.e(TAG, "afterOnCreateView: " +it.body())
            when(it.body()){
                null ->{
                    //TODO show to user error
                }
                else ->{
                    when(it.isSuccessful){
                        true -> {
                            var df = it.body()
                            Log.e(TAG, "afterOnCreateView: ${df?.products?.get(5)}")
                        }
                        else->{
                            //TODO check $

                        }
                    }
                }
            }
        }

        mViewModel.requestCategory("ACCESSORIES")

    }

    private fun initTypeRecycler(){
        val _layoutManager = LinearLayoutManager(context)
        _layoutManager.orientation = RecyclerView.VERTICAL
        var banerList = listOf("Accessories", "SHOES", "T-SHIRTS")
         var  typeProductAdapter = TypeProductAdapter(banerList)
        binding.filterRecyclerview.apply {
             layoutManager=_layoutManager
            adapter = typeProductAdapter
        }

    }
}