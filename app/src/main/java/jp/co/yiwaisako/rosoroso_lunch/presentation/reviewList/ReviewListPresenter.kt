package jp.co.yiwaisako.rosoroso_lunch.presentation.reviewList

import com.google.firebase.firestore.FirebaseFirestore
import jp.co.yiwaisako.rosoroso_lunch.domain.model.Restaurant
import jp.co.yiwaisako.rosoroso_lunch.domain.model.Review


class ReviewListPresenter(val view: ReviewListContract.View) : ReviewListContract.Presenter {
    private val fireStore = FirebaseFirestore.getInstance()

    override fun onCreate() {

        fireStore.collection("restaurants")
            .document("84J5T328R58mbdRWhMb8")
            .collection("reviews")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result ?: return@addOnCompleteListener
                    val data: List<Review> = result.toObjects(Review::class.java)
                    view.setup(data)
                }
            }
    }
}