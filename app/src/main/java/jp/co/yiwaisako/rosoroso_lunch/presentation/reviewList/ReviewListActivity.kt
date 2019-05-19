package jp.co.yiwaisako.rosoroso_lunch.presentation.reviewList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.co.yiwaisako.rosoroso_lunch.R
import jp.co.yiwaisako.rosoroso_lunch.databinding.ActivityReviewListBinding
import jp.co.yiwaisako.rosoroso_lunch.domain.model.Review
import jp.co.yiwaisako.rosoroso_lunch.presentation.reviewPosting.ReviewPostingActivity
import jp.co.yiwaisako.rosoroso_lunch.util.ext.contentViewBinding
import jp.co.yiwaisako.rosoroso_lunch.util.ext.toGone


class ReviewListActivity : AppCompatActivity(), ReviewListContract.View {

    companion object {
        @JvmStatic
        fun createIntent(context: Context) =
            Intent(context, ReviewListActivity::class.java)
    }

    private val binding: ActivityReviewListBinding by contentViewBinding(R.layout.activity_review_list)
    private lateinit var presenter: ReviewListContract.Presenter
    private val controller by lazy { ReviewListController(presenter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ReviewListPresenter(this)

        binding.apply {
            recyclerView.setController(controller)
        }

        presenter.onCreate()
    }

    override fun setup(data: List<Review>) {
        binding.progressbar.toGone()
        controller.setData(data)

        binding.fab.setOnClickListener {
            ReviewPostingActivity.createIntent(this).apply {
                startActivity(this)
            }
        }
    }
}
