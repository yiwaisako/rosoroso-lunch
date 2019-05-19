package jp.co.yiwaisako.rosoroso_lunch.presentation.reviewPosting

import com.google.firebase.firestore.FirebaseFirestore
import jp.co.yiwaisako.rosoroso_lunch.domain.model.Review


class ReviewPostingPresenter(
    val view: ReviewPostingContract.View
) : ReviewPostingContract.Presenter {

    private val fireStore = FirebaseFirestore.getInstance()

    override fun onClickRegister(review: Review) {
        if (canRegist(review)) {
            registe(review)
        }
    }

    private fun canRegist(review: Review): Boolean {
        if (review.body.isEmpty()) return false
        return true
    }

    private fun registe(review: Review) {
        // TODO
    }
}