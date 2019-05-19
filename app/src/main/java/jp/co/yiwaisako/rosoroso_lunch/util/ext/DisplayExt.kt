package jp.co.yiwaisako.rosoroso_lunch.util.ext

import android.content.res.Resources

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()