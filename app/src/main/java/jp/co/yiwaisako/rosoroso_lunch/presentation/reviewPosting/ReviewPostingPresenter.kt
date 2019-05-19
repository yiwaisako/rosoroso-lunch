package jp.co.yiwaisako.rosoroso_lunch.presentation.reviewPosting

import com.google.firebase.firestore.FirebaseFirestore
import jp.co.yiwaisako.rosoroso_lunch.BuildConfig
import jp.co.yiwaisako.rosoroso_lunch.domain.model.Review


class ReviewPostingPresenter(
    val view: ReviewPostingContract.View
) : ReviewPostingContract.Presenter {

    private val fireStore = FirebaseFirestore.getInstance()

    override fun onClickRegister(restaurantDocumentId: String, review: Review) {
        if (canRegister(review)) {
            register(restaurantDocumentId, review)
        }
    }

    private fun canRegister(review: Review): Boolean {
        if (review.body.isEmpty()) return false
        return true
    }

    private fun register(restaurantDocumentId: String, review: Review) {
        fireStore.collection(BuildConfig.restaurants)
            .document(restaurantDocumentId)
            .collection(BuildConfig.reviews)
            .add(review)
            .addOnCompleteListener { }
            .addOnSuccessListener {
                view.showToast("送信完了")
                view.close()
            }
            .addOnFailureListener {
                view.showToast("送信失敗")
            }
    }
}