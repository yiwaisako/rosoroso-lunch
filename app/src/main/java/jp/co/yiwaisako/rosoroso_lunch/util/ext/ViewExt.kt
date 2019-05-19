package jp.co.yiwaisako.rosoroso_lunch.util.ext

import android.content.Context
import android.databinding.BindingAdapter
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.FrameLayout

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.setRoundedCorner(c: Context, color: Int, radius: Float) {
    GradientDrawable().let {
        it.cornerRadius = radius
        it.setColor(color)
        this.background = it
    }
}

@BindingAdapter("widthDp", "heightDp", requireAll = true)
fun FrameLayout.setLayoutParams(width: Int?, height: Int?) {
    width ?: return
    height ?: return
    layoutParams = FrameLayout.LayoutParams(width.toPx(), height.toPx())
}

