package jp.co.yiwaisako.rosoroso_lunch.presentation.reviewPosting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.co.yiwaisako.rosoroso_lunch.R
import jp.co.yiwaisako.rosoroso_lunch.databinding.ActivityReviewPostingBinding
import jp.co.yiwaisako.rosoroso_lunch.domain.model.Review
import jp.co.yiwaisako.rosoroso_lunch.util.ext.contentViewBinding


class ReviewPostingActivity : AppCompatActivity(), ReviewPostingContract.View {

    companion object {
        @JvmStatic
        fun createIntent(context: Context) =
            Intent(context, ReviewPostingActivity::class.java)
    }

    private val binding: ActivityReviewPostingBinding by contentViewBinding(R.layout.activity_review_posting)
    private lateinit var presenter: ReviewPostingContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_posting)
        presenter = ReviewPostingPresenter(this)
        setup()
    }

    override fun close() {
        finish()
    }

    private fun setup() {
        binding.registerButton.setOnClickListener {
            val b = binding.body.text.toString()
            val s = binding.starsSpinner.selectedItem.toString().toInt()

            Review(body = b, stars = s).apply {
                presenter.onClickRegister(this)
            }
        }
    }
}
