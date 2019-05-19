package jp.co.yiwaisako.rosoroso_lunch.presentation.restaurantRegister

interface RestaurantRegisterContract {

    interface View : ScreenTransition {
        fun showToast(messsage: String)
    }

    interface Presenter {
        fun onClickRegister(name: String, url: String, stationKey: String)
    }

    interface ScreenTransition {
        fun close()
    }
}