package jp.co.yiwaisako.rosoroso_lunch.presentation.restaurantRegister

import com.google.firebase.firestore.FirebaseFirestore
import jp.co.yiwaisako.rosoroso_lunch.BuildConfig
import jp.co.yiwaisako.rosoroso_lunch.domain.model.Restaurant


class RestaurantRegisterPresenter(
    val view: RestaurantRegisterContract.View
) : RestaurantRegisterContract.Presenter {

    private val fireStore = FirebaseFirestore.getInstance()

    override fun onClickRegister(name: String, url: String, stationKey: String) {
        val restaurant = Restaurant(
            name = name,
            url = url,
            station = fireStore.document(stationKey)
        )
        if (canRegister(restaurant)) {
            register(restaurant)
        }
    }

    private fun canRegister(restaurant: Restaurant): Boolean {
        if (restaurant.name.isEmpty()) return false
        if (restaurant.url.isEmpty()) return false
        return true
    }

    private fun register(restaurant: Restaurant) {
        fireStore.collection(BuildConfig.restaurants)
            .add(restaurant)
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