package app.storytel.candidate.com.features.splash

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.storytel.candidate.com.R
import app.storytel.candidate.com.databinding.FragmentSplashBinding
import app.storytel.candidate.com.features.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun getViewBinding(): FragmentSplashBinding =
        FragmentSplashBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        runLogoAnimation()
    }

    private fun runLogoAnimation() {

        val slideAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        binding.imageViewAppIcon.startAnimation(slideAnimation)

        lifecycleScope.launch {
            delay(SPLASH_NAVIGATION_DELAY)
            navigateToPostListFragment()
        }
    }

    private fun navigateToPostListFragment() {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToPostListFragment())
    }

    companion object {
        private const val SPLASH_NAVIGATION_DELAY: Long = 2000
    }
}
