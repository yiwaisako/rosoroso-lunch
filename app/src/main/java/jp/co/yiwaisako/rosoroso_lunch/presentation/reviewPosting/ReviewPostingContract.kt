package jp.co.yiwaisako.rosoroso_lunch.presentation.reviewPosting

import jp.co.yiwaisako.rosoroso_lunch.domain.model.Review

interface ReviewPostingContract {

    interface View : ScreenTransition {
        fun showToast(messsage: String)
    }

    interface Presenter {
        fun onClickRegister(restaurantDocumentId: String, review: Review)
    }

    interface ScreenTransition {
        fun close()
    }
}