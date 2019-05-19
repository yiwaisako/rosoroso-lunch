package jp.co.yiwaisako.rosoroso_lunch.domain.model

import java.util.*

data class Restaurant(
    var id: String = "",
    val name: String = "",
    val createTime: Date = Date(),
    val url: String = "",
    var averageStars: Int = 0
)