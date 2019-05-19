package jp.co.yiwaisako.rosoroso_lunch.presentation.reviewList

import jp.co.yiwaisako.rosoroso_lunch.domain.model.Review

interface ReviewListContract {
    interface View : ScreenTransition {
        fun setup(data: List<Review>)
    }

    interface Presenter {
        fun onCreate()
    }

    interface ScreenTransition {
        // NOP
    }
}