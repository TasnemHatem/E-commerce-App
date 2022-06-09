package com.example.e_commerceapp.ui.search.view


import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentSearchBinding
import com.example.e_commerceapp.ui.category.CategorAdapter
import com.example.e_commerceapp.ui.category.model.Product
import com.example.e_commerceapp.ui.category.model.ProductsResponse
import com.example.e_commerceapp.ui.category.view.OnClickCategoryListener
import com.example.e_commerceapp.ui.category.view.OnClickTopFilterListener
import com.example.e_commerceapp.ui.category.viewmodel.CategoryViewModel
import com.example.e_commerceapp.ui.search.viewmodel.SearchViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "SearchFragment"
@AndroidEntryPoint
class SearchFragment: BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate), OnClickCategoryListener,
    OnClickTopFilterListener {

    val mViewModel: SearchViewModel by viewModels()
    lateinit var  searchAdapter : SearchAdapter
     var products = listOf<Product>()


    override fun afterOnCreateView() {
        super.afterOnCreateView()
        initSearchRecycler()
        binding.backSearch.setOnClickListener {
            navController.navigate(R.id.action_searchFragment_to_mainFragment2)
        }
        binding.SearchBarFrag.isIconifiedByDefault = false

        mViewModel.requestAllProducts()

        mViewModel.searchProd.observeInFragment(viewLifecycleOwner){
            Log.e(TAG, "afterOnCreateView: " +it.body())
            when(it.body()){
                null ->{
                    showError()
                    val snackBar = Snackbar.make(binding.root, "No Internet Connection", Snackbar.LENGTH_LONG)
                    snackBar.view.setBackgroundColor(Color.RED)
                    snackBar.show()
                }
                else ->{
                    when(it.isSuccessful){
                        true -> {
                            searchAdapter.setListTOAdapter(it.body()!!.products)
                            products=it.body()!!.products
                        }
                        else->{
                            showError()
                            val snackBar = Snackbar.make(binding.root, "someThing went wrong", Snackbar.LENGTH_LONG)
                            snackBar.view.setBackgroundColor(Color.RED)
                            snackBar.show()

                        }
                    }
                }
            }
        }

        binding.SearchBarFrag.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                showItem()
                filterAll(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                showItem()
                filterAll(newText)
                return false
            }
        })
    }

    private fun initSearchRecycler(){
        val grid = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        searchAdapter = SearchAdapter(listOf(),this)
        binding.recycleSearch.apply {
            layoutManager = grid
            adapter = searchAdapter
        }
    }

    override fun viewProductDetailes(product:Product) {
        val bundle = bundleOf("product" to product)
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_searchFragment_to_productDetailes,bundle)
    }

    override fun filterAll(productName: String) {
        var afterFilter = listOf<Product>()
        afterFilter = products.filter { s -> productName in s.tags }
        if (afterFilter.isNotEmpty()) {
            searchAdapter.setListTOAdapter(afterFilter)
        }else{
            showError()
        }
    }

    private fun showError(){
        binding.recycleSearch.visibility= View.GONE
        binding.imageError.visibility= View.VISIBLE
    }
    private fun showItem(){
        binding.recycleSearch.visibility= View.VISIBLE
        binding.imageError.visibility= View.GONE
    }


}