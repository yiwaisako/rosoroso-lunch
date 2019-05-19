package jp.co.yiwaisako.rosoroso_lunch.presentation.reviewPosting

import jp.co.yiwaisako.rosoroso_lunch.domain.model.Review

interface ReviewPostingContract {

    interface View : ScreenTransition {
        // NOP
    }

    interface Presenter {
        fun onClickRegister(review: Review)
    }

    interface ScreenTransition {
        fun close()
    }
}