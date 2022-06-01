package com.example.e_commerceapp.ui.category

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentCategoryBinding
import com.example.e_commerceapp.ui.category.model.Product
import com.example.e_commerceapp.ui.category.model.ProductsResponse
import com.example.e_commerceapp.ui.category.view.OnClickCategoryListener
import com.example.e_commerceapp.ui.category.view.OnClickFilterListener
import com.example.e_commerceapp.ui.category.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "CategoryFragment"
@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding> (FragmentCategoryBinding::inflate),OnClickCategoryListener,OnClickFilterListener{
    val mViewModel: CategoryViewModel by viewModels()
    lateinit var  categorAdapter :CategorAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTypeRecycler()
        initCategoryRecycler()

        mViewModel.categoryy.observeInFragment(viewLifecycleOwner){
            Log.e(TAG, "afterOnCreateView: " +it.body())
            when(it.body()){
                null ->{
                    //TODO show to user error
                }
                else ->{
                    when(it.isSuccessful){
                        true -> {
                            categorAdapter.setListTOAdaptr(it.body()!!)
                        }
                        else->{
                            //TODO check $

                        }
                    }
                }
            }
        }

    }

    private fun initTypeRecycler(){
        val _layoutManager = LinearLayoutManager(context)
        _layoutManager.orientation = RecyclerView.VERTICAL
        var banerList = listOf("Accessories", "SHOES", "T-SHIRTS")
        var  typeProductAdapter = TypeProductAdapter(banerList,this)
        binding.filterRecyclerview.apply {
             layoutManager=_layoutManager
            adapter = typeProductAdapter
        }

    }

    private fun initCategoryRecycler(){
        val grid = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        categorAdapter = CategorAdapter(ProductsResponse(listOf()),this)
         binding.categoryRecyclerview.apply {
             layoutManager = grid
             adapter = categorAdapter
         }
    }

    override fun viewProductDetailes() {
    }

    override fun filter(productType: String) {
        mViewModel.requestCategory(productType)

    }
}