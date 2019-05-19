package jp.co.yiwaisako.rosoroso_lunch.presentation.reviewList

import com.airbnb.epoxy.TypedEpoxyController
import jp.co.yiwaisako.rosoroso_lunch.domain.model.Review
import jp.co.yiwaisako.rosoroso_lunch.itemEmpty
import jp.co.yiwaisako.rosoroso_lunch.itemReview
import timber.log.Timber

class ReviewListController(
    private val presenter: ReviewListContract.Presenter
) : TypedEpoxyController<List<Review>>() {

    override fun buildModels(data: List<Review>) {
        when (data.isEmpty()) {
            true -> {
                itemEmpty {
                    id("itemEmpty")
                }
            }
            false -> {
                data.forEach { review ->
                    Timber.d(review.body)
                    itemReview {
                        id("review $review")
                        body(review.body)
                        stars(review.stars.toString())
                    }
                }
            }
        }
    }
}