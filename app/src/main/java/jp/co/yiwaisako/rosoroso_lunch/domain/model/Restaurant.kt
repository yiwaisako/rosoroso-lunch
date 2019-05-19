package jp.co.yiwaisako.rosoroso_lunch.domain.model

import com.google.firebase.firestore.DocumentReference
import java.util.*

data class Restaurant(
    var id: String = "",
    val name: String = "",
    val station: DocumentReference? = null,
    val createTime: Date = Date(),
    val url: String = "",
    var averageStars: Int = 0
)