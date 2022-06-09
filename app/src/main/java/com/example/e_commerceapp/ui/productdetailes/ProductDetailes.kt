package com.example.e_commerceapp.ui.productdetailes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.navigation.Navigation
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentProductdetailesBinding
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.category.model.Product
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailes: BaseFragment<FragmentProductdetailesBinding>(FragmentProductdetailesBinding::inflate) {

    @Inject
    lateinit var appSharedPreference: AppSharedPreference

    lateinit var  bindingAddToFavourite : ImageView
    lateinit var  bindingDeletFromFavourite : ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var product = arguments?.getParcelable<Product>("product")
        initUI(product!!)
        addToFavourite()
        deletFromFavourite()
        addToBag()
        backToHome()
        goToCart()
        goToWishList()
    }


    private fun initUI(product: Product){
        val _sliderAdapter = SliderAdapterProductDetailes(product.images)
        binding.imageSliderProductDetailes.setSliderAdapter(_sliderAdapter)
        binding.imageSliderProductDetailes.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.imageSliderProductDetailes.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        binding.imageSliderProductDetailes.startAutoCycle()
        binding.productDetailesName.text = product.title
        binding.productDetailesPrice.text = "US$ ${product?.variants?.get(0)?.price}"
        binding.productDescription.text= product.bodyHtml
        bindingAddToFavourite  = binding.addToFavouriteFromDetailes
        bindingDeletFromFavourite = binding.deleteToFavouriteFromDetailes

    }

    private fun addToFavourite(){
        bindingAddToFavourite.setOnClickListener{
            if (appSharedPreference.getBooleanValue("login")) {
                //
                bindingAddToFavourite.visibility = View.GONE
                bindingDeletFromFavourite.visibility = View.VISIBLE
            }else{
                goToLogin()
            }
        }

    }

    private fun deletFromFavourite(){
       bindingDeletFromFavourite.setOnClickListener{
           if (appSharedPreference.getBooleanValue("login")) {
                //

               bindingAddToFavourite.visibility=View.VISIBLE
               bindingDeletFromFavourite.visibility = View.GONE
           }else{
               goToLogin()
           }

       }
    }

    private fun addToBag(){
        binding.addToBag.setOnClickListener{
            if (appSharedPreference.getBooleanValue("login")) {
                Log.i("add", "addToBag")
            }else{
                goToLogin()
            }
        }
    }

    private fun backToHome(){
        binding.backFromProductDetailesTOMain.setOnClickListener{
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate( R.id.action_productDetailes_to_mainFragment)

        }
    }

    private fun goToLogin(){
        navController.navigate(R.id.action_productDetailes_to_loginFragment)

    }

    private fun goToWishList(){
      binding.btnFavoriteProductDetailes.setOnClickListener{
          if (appSharedPreference.getBooleanValue("login")) {
              navController.navigate(R.id.action_productDetailes_to_wishlistFragment)
          }else{
              goToLogin()
          }

      }
    }


    private fun goToCart(){
        binding.btnCartProductDetailes.setOnClickListener{
            if (appSharedPreference.getBooleanValue("login")) {
                navController.navigate(R.id.action_productDetailes_to_cartFragment)
            }else{
                goToLogin()
            }

        }
    }

}