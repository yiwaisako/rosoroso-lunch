package jp.co.yiwaisako.rosoroso_lunch.presentation.restaurantList

import com.google.firebase.firestore.FirebaseFirestore
import jp.co.yiwaisako.rosoroso_lunch.domain.model.Restaurant
import timber.log.Timber


class RestaurantListPresenter(val view: RestaurantListContract.View) :
    RestaurantListContract.Presenter {
    private val fireStore = FirebaseFirestore.getInstance()

    override fun onCreate(stationKey: String) {
        fetchRestaurants(stationKey)
    }

    override fun onClickRestaurant(documentId: String) {
        Timber.d("onClickRestaurant")
        view.moveToReviewList(documentId)
    }

    private fun fetchRestaurants(stationKey: String) {
        fireStore.collection("restaurants")
            .whereEqualTo("station", fireStore.document(stationKey))
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result ?: return@addOnCompleteListener

                    val data: ArrayList<Restaurant> = arrayListOf()
                    result.documents.forEach {
                        it.toObject(Restaurant::class.java)?.let { restaurant ->
                            restaurant.id = it.id
                            data.add(restaurant)
                        }
                    }
                    view.setup(data)
                }
            }
    }
}