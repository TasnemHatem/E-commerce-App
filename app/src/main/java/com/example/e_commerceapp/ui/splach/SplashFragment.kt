package com.example.e_commerceapp.ui.splach

import androidx.lifecycle.lifecycleScope
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.base.utils.safeNavigation
import com.example.e_commerceapp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun afterOnViewCreated() {
        super.afterOnViewCreated()
        lifecycleScope.launch(Dispatchers.Default) {
            delay(3500L)
            withContext(Dispatchers.Main){
                navController.safeNavigation(R.id.splashFragment,R.id.action_splashFragment_to_mainFragment)
            }
        }
    }
}