package com.okanaydin.assignment.features.splash

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.okanaydin.assignment.com.R
import com.okanaydin.assignment.com.databinding.FragmentSplashBinding
import com.okanaydin.assignment.features.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun getViewBinding(): FragmentSplashBinding =
        FragmentSplashBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startViewAnimation()
    }

    private fun startViewAnimation() {

        val slideAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        binding.imageViewAppIcon.startAnimation(slideAnimation)

        slideAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) = Unit

            override fun onAnimationEnd(animation: Animation?) {
                navigateToPostListFragment()
            }

            override fun onAnimationRepeat(animation: Animation?) = Unit
        })
    }

    private fun navigateToPostListFragment() {
        findNavController().navigate(com.okanaydin.assignment.features.splash.SplashFragmentDirections.actionSplashFragmentToPostListFragment())
    }
}
