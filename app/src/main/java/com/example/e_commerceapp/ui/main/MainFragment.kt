package com.example.e_commerceapp.ui.main

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
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


        binding.btnFavorite.setOnClickListener{
            navController.navigate(R.id.action_mainFragment_to_wishlistFragment)
        }

        binding.btnCart.setOnClickListener{
            navController.navigate(R.id.action_mainFragment_to_cartFragment)
        }

        binding.settingsBtnId.setOnClickListener{
            navController.navigate(R.id.action_mainFragment_to_settingsFragment)
        }
        binding.SearchBar.isIconifiedByDefault = true

        binding.SearchBar.setOnSearchClickListener{
            navController.navigate(R.id.action_mainFragment_to_searchFragment2)
        }

    }

    private fun setUpBottomNavigation() {
        localNavController =
            (childFragmentManager.findFragmentById(R.id.nav_host_fragment_home) as NavHostFragment).navController
        binding.bottomNavigationView.setupWithNavController(localNavController)
    }


    private fun setOnDestinationChangedListener() {
        localNavController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.homeFragment ->{
                    binding.SearchBar.visibility=View.VISIBLE
                }
                R.id.categoryFragment ->{
                    binding.SearchBar.visibility=View.VISIBLE

                }
                R.id.meFragment ->{
                    binding.settingsBtnId.visibility=View.VISIBLE
                    binding.SearchBar.visibility=View.GONE
                }


            }
        }
    }

}