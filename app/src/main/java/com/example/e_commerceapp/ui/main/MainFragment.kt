package com.example.e_commerceapp.ui.main

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentMainBinding
import com.example.e_commerceapp.utils.ConnectionLiveData

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate){
    lateinit var localNavController: NavController
    lateinit var connectionLiveData: ConnectionLiveData
    override fun afterOnViewCreated() {
        super.afterOnViewCreated()
        listenerToNetwork()

//        connectionLiveData = ConnectionLiveData(requireContext())
////        connectionLiveData.observe(this,{isNetworkAvailable->
////            binding.constrainCheckNetwork.visibility = View.GONE
////
////        })
//
//                setContent {
//
//        }
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
                    binding.settingsBtnId.visibility=View.GONE

                }
                R.id.categoryFragment ->{
                    binding.SearchBar.visibility=View.VISIBLE
                    binding.settingsBtnId.visibility=View.GONE

                }
                R.id.meFragment ->{
                    binding.settingsBtnId.visibility=View.VISIBLE
                    binding.SearchBar.visibility=View.GONE
                }


            }
        }
    }

    private fun listenerToNetwork() {
        ConnectionLiveData(requireContext()).observe(this, {
            if (it) {
                binding.constrainCheckNetwork.visibility = View.VISIBLE
                binding.imageView.visibility = View.GONE
            } else {
                binding.constrainCheckNetwork.visibility = View.GONE
                binding.imageView.visibility = View.VISIBLE
            }
        })
    }

}