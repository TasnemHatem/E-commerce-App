package com.example.e_commerceapp.ui.splach

import android.animation.Animator
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.base.utils.safeNavigation
import com.example.e_commerceapp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint


interface AnimatorEndListener : Animator.AnimatorListener {
    override fun onAnimationStart(animation: Animator?, isReverse: Boolean) {
    }

    override fun onAnimationStart(animation: Animator?) {
    }

    override fun onAnimationCancel(animation: Animator?) {
    }

    override fun onAnimationRepeat(animation: Animator?) {
    }
}

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun afterOnViewCreated() {
        super.afterOnViewCreated()
        binding.animation.addAnimatorListener(object : AnimatorEndListener {
            override fun onAnimationEnd(animation: Animator?) {
                navController.safeNavigation(R.id.splashFragment,
                    R.id.action_splashFragment_to_mainFragment)
            }
        })
//        lifecycleScope.launch(Dispatchers.Default) {
//            delay(3500L)
//            withContext(Dispatchers.Main) {
//            }
//        }
    }
}