package com.example.e_commerceapp.ui.reviews

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentReviewsBinding
import com.example.e_commerceapp.ui.reviews.model.getAllReviews
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewsFragment : BaseFragment<FragmentReviewsBinding>(FragmentReviewsBinding::inflate)  {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initReviewsRecycler()
        binding.backFromReviewsToproductDetailes.setOnClickListener {
            navController.navigate(R.id.action_reviewsFragment_to_mainFragment)

        }

    }

    private fun initReviewsRecycler(){
        val _layoutManager = LinearLayoutManager(context)
        _layoutManager.orientation = RecyclerView.VERTICAL
        var reviewAdapter = RewiewsAdapter(getAllReviews())
        binding.reviewRecycler.apply {
            adapter = reviewAdapter
            layoutManager= _layoutManager
        }
    }
}