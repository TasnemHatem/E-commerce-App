package com.example.e_commerceapp.ui.category

import android.content.SharedPreferences
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
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.category.model.ProductsResponse
import com.example.e_commerceapp.ui.category.view.OnClickCategoryListener
import com.example.e_commerceapp.ui.category.view.OnClickFilterListener
import com.example.e_commerceapp.ui.category.view.OnClickTopFilterListener
import com.example.e_commerceapp.ui.category.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "CategoryFragment"
@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding> (FragmentCategoryBinding::inflate),OnClickCategoryListener,OnClickFilterListener,OnClickTopFilterListener{
    val mViewModel: CategoryViewModel by viewModels()
    lateinit var  categorAdapter :CategorAdapter

    @Inject
    lateinit var appSharedPreferences: AppSharedPreference



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTypeRecycler()
        initCategoryRecycler()
        initTopTypeRecycler()

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

        mViewModel.requestAllCategory()

    }

    private fun initTypeRecycler(){
        val _layoutManager = LinearLayoutManager(context)
        _layoutManager.orientation = RecyclerView.VERTICAL
        var banerList = listOf("Accessories", "SHOES", "T-SHIRTS")
        var  typeProductAdapter = TypeProductAdapter(banerList,this,appSharedPreferences)
        binding.filterRecyclerview.apply {
             layoutManager=_layoutManager
            adapter = typeProductAdapter
        }

    }

    private fun initTopTypeRecycler(){
        val _layoutManager = LinearLayoutManager(context)
        _layoutManager.orientation = RecyclerView.HORIZONTAL
        var banerList = listOf("Women", "Kid", "Men" , "Sale")
        var topTypeProductAdapter = TopTypeProductAdapter(banerList,this)
        binding.topFilterRecycler.apply {
            layoutManager=_layoutManager
            adapter = topTypeProductAdapter
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

    override fun filter(productType: String,topProductType:String) {
       mViewModel.requestCategory(productType)

        mViewModel.categoryy.observeInFragment(viewLifecycleOwner){
            Log.e(TAG, "afterOnCreateView: " +it.body())
            when(it.body()){
                null ->{
                    //TODO show to user error
                }
                else ->{
                    when(it.isSuccessful){
                        true -> {
                            var item=   it.body()
                            var aftrefilter = item?.products?.filter { s -> s.tags.contains(topProductType,true) }
                            if (aftrefilter != null) {
                                item?.products = aftrefilter
                            }
                            categorAdapter.setListTOAdaptr(item!!)
                           // isVisable(item)
                        }
                        else->{
                            //TODO check $

                        }
                    }
                }
            }
        }

    }

    override fun filterAll(productType: String) {

       appSharedPreferences.setValue("index",productType)

        mViewModel.requestAllCategory()
        mViewModel.categoryy.observeInFragment(viewLifecycleOwner){
            Log.e(TAG, "afterOnCreateView: " +it.body())
            when(it.body()){
                null ->{
                    //TODO show to user error
                }
                else ->{
                    when(it.isSuccessful){
                        true -> {
                            var item=   it.body()
                            var aftrefilter = item?.products?.filter { s -> s.tags.contains(productType,true) }
                            if (aftrefilter != null) {
                                item?.products = aftrefilter
                            }
                            categorAdapter.setListTOAdaptr(item!!)
                           // isVisable(item)
                        }
                        else->{
                            //TODO check $

                        }
                    }
                }
            }
        }
    }

    fun isVisable( category: ProductsResponse){
        if(category.products.size==0) {
            binding.imageVisable.visibility = View.VISIBLE
            binding.categoryRecyclerview.visibility = View.GONE
        }else{
            binding.imageVisable.visibility = View.GONE
            binding.categoryRecyclerview.visibility = View.VISIBLE
        }
    }
}