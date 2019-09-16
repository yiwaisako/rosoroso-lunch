package jp.co.yiwaisako.rosoroso_lunch.presentation.reviewPosting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import jp.co.yiwaisako.rosoroso_lunch.R
import jp.co.yiwaisako.rosoroso_lunch.databinding.ActivityReviewPostingBinding
import jp.co.yiwaisako.rosoroso_lunch.domain.model.Review
import jp.co.yiwaisako.rosoroso_lunch.util.ext.contentViewBinding


class ReviewPostingActivity : AppCompatActivity(), ReviewPostingContract.View {

    companion object {
        private const val EXTRA_DOCUMENT_ID = "documentId"

        @JvmStatic
        fun createIntent(context: Context, restaurantDocumentId: String) =
            Intent(context, ReviewPostingActivity::class.java).apply {
                putExtra(EXTRA_DOCUMENT_ID, restaurantDocumentId)
            }
    }

    private val binding: ActivityReviewPostingBinding by contentViewBinding(R.layout.activity_review_posting)
    private lateinit var presenter: ReviewPostingContract.Presenter
    private var documentId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_posting)
        presenter = ReviewPostingPresenter(this)

        documentId = savedInstanceState?.getString(EXTRA_DOCUMENT_ID, "")
            ?: intent.getStringExtra(EXTRA_DOCUMENT_ID)

        setup()
    }

    override fun close() {
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun showToast(messsage: String) {
        Toast.makeText(this, messsage, Toast.LENGTH_LONG).show()
    }

    private fun setup() {
        binding.registerButton.setOnClickListener {
            val b = binding.body.text.toString()
            val s = binding.starsSpinner.selectedItem.toString().toInt()

            Review(body = b, stars = s).apply {
                presenter.onClickRegister(documentId, this)
            }
        }
    }
}
