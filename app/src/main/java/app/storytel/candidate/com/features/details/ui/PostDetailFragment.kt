package app.storytel.candidate.com.features.details.ui

import android.os.Bundle
import android.view.View
import app.storytel.candidate.com.R
import app.storytel.candidate.com.databinding.FragmentPostDetailBinding
import app.storytel.candidate.com.features.base.BaseFragment

class PostDetailFragment : BaseFragment<FragmentPostDetailBinding>() {

    override fun getViewBinding(): FragmentPostDetailBinding =
        FragmentPostDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureToolBar()
        // TODO display the selected post from ScrollingActivity. Use mImageView and mTextView for image and body text. Change the title to use the post title
        // TODO load top 3 comments from COMMENTS_URL into the 3 card views
    }

    private fun configureToolBar() {
        binding.toolbar.title = getString(R.string.app_name)
    }
}
