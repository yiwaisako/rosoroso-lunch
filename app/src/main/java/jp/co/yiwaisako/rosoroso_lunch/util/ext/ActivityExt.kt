package jp.co.yiwaisako.rosoroso_lunch.util.ext

import android.app.Activity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> Activity.contentViewBinding(layout: Int): Lazy<T> = lazy {
    DataBindingUtil.setContentView<T>(this, layout)
}
