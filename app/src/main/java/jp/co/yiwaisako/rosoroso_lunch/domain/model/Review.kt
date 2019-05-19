package jp.co.yiwaisako.rosoroso_lunch.domain.model

import java.util.*

data class Review(
    val body: String = "",
    val stars: Int = 0,
    val createTime: Date = Date()
)
