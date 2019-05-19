package jp.co.yiwaisako.rosoroso_lunch.presentation.restaurantList

import jp.co.yiwaisako.rosoroso_lunch.domain.model.Restaurant

interface RestaurantListContract {
    interface View : ScreenTransition {
        fun setup(data: List<Restaurant>)
    }

    interface Presenter {
        fun onCreate(stationDocument: String)
        fun onClickRestaurant(documentId: String)
    }

    interface ScreenTransition {
        fun moveToReviewList(documentId: String)
    }
}