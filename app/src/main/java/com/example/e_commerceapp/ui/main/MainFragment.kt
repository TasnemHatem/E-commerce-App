package com.example.e_commerceapp.ui.main

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate){
    lateinit var localNavController: NavController

    override fun afterOnViewCreated() {
        super.afterOnViewCreated()
        setUpBottomNavigation()
        setOnDestinationChangedListener()
    }

    private fun setUpBottomNavigation() {
        localNavController =
            (childFragmentManager.findFragmentById(R.id.nav_host_fragment_home) as NavHostFragment).navController
        binding.bottomNavigationView.setupWithNavController(localNavController)
    }


    open fun setOnDestinationChangedListener(){

    }

}