package jp.co.yiwaisako.rosoroso_lunch.presentation.reviewList

import android.app.Activity
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
        private const val EXTRA_DOCUMENT_ID = "documentId"
        private const val REQUEST_CODE_REVIEW_POSTING = 1000

        @JvmStatic
        fun createIntent(context: Context, documentId: String) =
            Intent(context, ReviewListActivity::class.java).apply {
                putExtra(EXTRA_DOCUMENT_ID, documentId)
            }
    }

    private val binding: ActivityReviewListBinding by contentViewBinding(R.layout.activity_review_list)
    private lateinit var presenter: ReviewListContract.Presenter
    private val controller by lazy { ReviewListController(presenter) }
    private var documentId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ReviewListPresenter(this)

        documentId = savedInstanceState?.getString(EXTRA_DOCUMENT_ID, "")
            ?: intent.getStringExtra(EXTRA_DOCUMENT_ID)

        binding.apply {
            recyclerView.setController(controller)
        }

        presenter.onCreate(documentId)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        data ?: return
        if (resultCode != Activity.RESULT_OK) return
        val bundle = data.extras ?: return
        when (requestCode) {
            REQUEST_CODE_REVIEW_POSTING -> {
                presenter.onCreate(documentId)
            }
        }
    }

    override fun setup(data: List<Review>) {
        binding.progressbar.toGone()
        controller.setData(data)

        binding.fab.setOnClickListener {
            ReviewPostingActivity.createIntent(this, documentId).apply {
                startActivityForResult(this, REQUEST_CODE_REVIEW_POSTING)
            }
        }
    }
}
