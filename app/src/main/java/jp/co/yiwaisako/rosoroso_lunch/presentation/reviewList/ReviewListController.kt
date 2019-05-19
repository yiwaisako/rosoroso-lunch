package jp.co.yiwaisako.rosoroso_lunch.presentation.reviewList

import com.airbnb.epoxy.TypedEpoxyController
import jp.co.yiwaisako.rosoroso_lunch.domain.model.Review
import jp.co.yiwaisako.rosoroso_lunch.itemReview
import jp.co.yiwaisako.rosoroso_lunch.itemSpace
import timber.log.Timber

class ReviewListController(
    private val presenter: ReviewListContract.Presenter
) : TypedEpoxyController<List<Review>>() {

    override fun buildModels(data: List<Review>) {
        data.forEach { review ->
            Timber.d(review.body)
            itemReview {
                id("review $review")
                body(review.body)
                stars(review.stars.toString())
            }
        }
    }

    private fun addSpace(height: Int = 16) {
        itemSpace {
            id(modelCountBuiltSoFar)
            widthDp(0)
            heightDp(height)
        }
    }
}