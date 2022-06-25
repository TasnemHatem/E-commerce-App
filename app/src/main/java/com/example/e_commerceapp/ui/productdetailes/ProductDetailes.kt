package com.example.e_commerceapp.ui.productdetailes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.network.DataState
import com.example.e_commerceapp.base.network.NetworkExceptions
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentProductdetailesBinding
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.cart.viewmodel.CartViewModel
import com.example.e_commerceapp.ui.category.model.Product
import com.example.e_commerceapp.ui.category.model.toListItem
import com.example.e_commerceapp.ui.reviews.RewiewsAdapter
import com.example.e_commerceapp.ui.reviews.model.getOnlyTwoReviews
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import java.math.RoundingMode
import javax.inject.Inject

private const val TAG = "ProductDetailes"

@AndroidEntryPoint
class ProductDetailes :
    BaseFragment<FragmentProductdetailesBinding>(FragmentProductdetailesBinding::inflate) {

    private var product: Product? = null

    @Inject
    lateinit var appSharedPreference: AppSharedPreference

    lateinit var bindingAddToFavourite: ImageView
    lateinit var bindingDeletFromFavourite: ImageView
    private val mViewModel: CartViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        product = arguments?.getParcelable<Product>("product")
        initUI(product!!)
        initReviewsRecycler()
        addToFavourite()
        deletFromFavourite()
        addToBag()
        backToHome()
        goToCart()
        goToWishList()
        observeViewModel()
        goToReviewsFragment()
    }

    private fun observeViewModel() {
        mViewModel.addItemResponse.observeInFragment(viewLifecycleOwner) { data ->
            when (data) {
                is DataState.Success -> {

                    Log.e(TAG, "afterOnCreateView: " + data)
                    if (binding.progressBarAddToCart.isVisible)
                    Toast.makeText(context,
                        resources.getString(R.string.added_successfully_to_cart),
                        Toast.LENGTH_LONG).show()
                    binding.apply {
                        progressBarAddToCart.visibility = View.GONE
                        addToBag.visibility = View.VISIBLE
                    }
                }
                is DataState.Error -> {
                    binding.apply {
                        progressBarAddToCart.visibility = View.GONE
                        addToBag.visibility = View.VISIBLE
                    }
                    displayMsg(data)
                }
                DataState.Loading -> {
                    binding.apply {
                        progressBarAddToCart.visibility = View.VISIBLE
                        addToBag.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun displayMsg(it: DataState.Error) {
        when (it.exception) {
            is NetworkExceptions.NetworkConnectionException -> {
                Toast.makeText(context,
                    resources.getString(R.string.no_network_connection),
                    Toast.LENGTH_LONG).show()
            }
            is NetworkExceptions.NotAllowedException,
            is NetworkExceptions.UnAuthorizedException,
            -> {
                Toast.makeText(context,
                    resources.getString(R.string.try_sign_out_and_signin_again),
                    Toast.LENGTH_LONG).show()
            }
            else -> {
                Toast.makeText(context,
                    resources.getString(R.string.something_went_wrong),
                    Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun initUI(product: Product) {
        val _sliderAdapter = SliderAdapterProductDetailes(product.images)
        binding.imageSliderProductDetailes.setSliderAdapter(_sliderAdapter)
        binding.imageSliderProductDetailes.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.imageSliderProductDetailes.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        binding.imageSliderProductDetailes.startAutoCycle()
        binding.productDetailesName.text = product.title

        var value= appSharedPreference.getStringValue("shared_currency_value").toDouble()
        val price = product.variants.get(0).price.toDouble()
        val temp = value* price
        val temp2 = "%.2f".format(temp)

        binding.productDetailesPrice.text = "${appSharedPreference.getStringValue("shared_currency_code")} ${temp2 }"
        binding.productDescription.text = product.bodyHtml
        bindingAddToFavourite = binding.addToFavouriteFromDetailes
        bindingDeletFromFavourite = binding.deleteToFavouriteFromDetailes
        if(product.isFavourite){
            bindingAddToFavourite.visibility = View.GONE
            bindingDeletFromFavourite.visibility=View.VISIBLE
        }else{
            bindingAddToFavourite.visibility = View.VISIBLE
            bindingDeletFromFavourite.visibility=View.GONE
        }
    }

    private fun initReviewsRecycler(){
        val _layoutManager = LinearLayoutManager(context)
        _layoutManager.orientation = RecyclerView.VERTICAL
        var reviewAdapter = RewiewsAdapter(getOnlyTwoReviews())
        binding.reviewsRecyclerView.apply {
            adapter = reviewAdapter
            layoutManager= _layoutManager
        }
    }

    private fun addToFavourite() {
        bindingAddToFavourite.setOnClickListener {
            if (appSharedPreference.getBooleanValue("login")) {
                //
                bindingAddToFavourite.visibility = View.GONE
                bindingDeletFromFavourite.visibility = View.VISIBLE
            } else {
                goToLogin()
            }
        }

    }

    private fun deletFromFavourite() {
        bindingDeletFromFavourite.setOnClickListener {
            if (appSharedPreference.getBooleanValue("login")) {
                //

                bindingAddToFavourite.visibility = View.VISIBLE
                bindingDeletFromFavourite.visibility = View.GONE
            } else {
                goToLogin()
            }

        }
    }

    private fun addToBag() {
        binding.addToBag.setOnClickListener {
            if (appSharedPreference.getBooleanValue("login")) {
                product?.toListItem()?.let { item->
                    mViewModel.addItem(item)
                }
            } else {
                goToLogin()
            }
        }
    }

    private fun backToHome() {
        binding.backFromProductDetailesTOMain.setOnClickListener {
            navController.navigateUp()
        }
    }

    private fun goToLogin() {
        navController.navigate(R.id.action_productDetailes_to_loginFragment)

    }

    private fun goToWishList() {
        binding.btnFavoriteProductDetailes.setOnClickListener {
            if (appSharedPreference.getBooleanValue("login")) {
                navController.navigate(R.id.action_productDetailes_to_wishlistFragment)
            } else {
                goToLogin()
            }

        }
    }


    private fun goToCart() {
        binding.btnCartProductDetailes.setOnClickListener {
            if (appSharedPreference.getBooleanValue("login")) {
                navController.navigate(R.id.action_productDetailes_to_cartFragment)
            } else {
                goToLogin()
            }

        }
    }

    private fun goToReviewsFragment(){
        binding.textViewMore.setOnClickListener {
            navController.navigate(R.id.action_productDetailes_to_reviewsFragment)

        }
    }

}