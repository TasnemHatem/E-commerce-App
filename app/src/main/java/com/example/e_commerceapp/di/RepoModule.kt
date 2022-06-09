package com.example.e_commerceapp.di

import com.example.e_commerceapp.ui.cart.network.CartService
import com.example.e_commerceapp.ui.cart.repo.CartRepo
import com.example.e_commerceapp.ui.cart.repo.CartRepoImpl
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.address.network.AddressService
import com.example.e_commerceapp.ui.address.repo.AddressRepo
import com.example.e_commerceapp.ui.address.repo.AddressRepoI
import com.example.e_commerceapp.ui.auth.network.AuthService
import com.example.e_commerceapp.ui.auth.repo.AuthRepo
import com.example.e_commerceapp.ui.auth.repo.AuthRepoImpl
import com.example.e_commerceapp.ui.category.network.CategoryService
import com.example.e_commerceapp.ui.category.repo.CategoryRepo
import com.example.e_commerceapp.ui.category.repo.CategoryRepoImpl
import com.example.e_commerceapp.ui.currency.network.CurrencyService
import com.example.e_commerceapp.ui.currency.repo.CurrencyRepo
import com.example.e_commerceapp.ui.home.network.VendorService
import com.example.e_commerceapp.ui.home.repo.VendorsRepo
import com.example.e_commerceapp.ui.home.repo.VendorsRepoImpl
import com.example.e_commerceapp.ui.newaddress.network.NewAddressService
import com.example.e_commerceapp.ui.newaddress.repo.NewAddressRepo
import com.example.e_commerceapp.ui.order.network.OrderService
import com.example.e_commerceapp.ui.order.repo.OrderRepo
import com.example.e_commerceapp.ui.wishlist.network.WishlistService
import com.example.e_commerceapp.ui.wishlist.repo.WishlistRepo
import com.example.e_commerceapp.ui.product.network.ProductService
import com.example.e_commerceapp.ui.product.repo.ProductRepo
import com.example.e_commerceapp.ui.product.repo.ProductRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepoModule {

    @ViewModelScoped
    @Provides
    fun getVendorsRepo(vendorService: VendorService):VendorsRepo{
        return VendorsRepoImpl(vendorService)
    }

    @ViewModelScoped
    @Provides
    fun getAuthRepo(authService: AuthService,appSharedPreference: AppSharedPreference): AuthRepo {
        return AuthRepoImpl(authService,appSharedPreference)
    }

    @ViewModelScoped
    @Provides
    fun getCategoryRepo(categoryService: CategoryService): CategoryRepo {
        return CategoryRepoImpl(categoryService)
    }
    
    @ViewModelScoped
    @Provides
    fun getCartRepo(cartService: CartService,appSharedPreference: AppSharedPreference):CartRepo{
        return CartRepoImpl(cartService,appSharedPreference)
    }
    
    @ViewModelScoped
    @Provides
    fun getWishlistRepo(wishlistService: WishlistService): WishlistRepo {
        return WishlistRepo(wishlistService)
    }

    @ViewModelScoped
    @Provides
    fun getAddressRepo(addressService: AddressService): AddressRepoI {
        return AddressRepo(addressService)
    }
    

    @ViewModelScoped
    @Provides
    fun getProductRepo(productService: ProductService):ProductRepo{
        return ProductRepoImpl(productService)
    }

    @ViewModelScoped
    @Provides
    fun getOrderRepo(orderService: OrderService): OrderRepo {
        return OrderRepo(orderService)
    }

    @ViewModelScoped
    @Provides
    fun getNewAddressRepo(newAddressService: NewAddressService): NewAddressRepo {
        return NewAddressRepo(newAddressService)
    }

    @ViewModelScoped
    @Provides
    fun getCurrencyRepo(currencyService: CurrencyService): CurrencyRepo {
        return CurrencyRepo(currencyService)
    }

}