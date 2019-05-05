package jp.co.yiwaisako.rosoroso_lunch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_station_list.*


class ReviewListActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun createIntent(context: Context) =
            Intent(context, ReviewListActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_list)

        nakameguro.setOnClickListener {
            RestaurantListActivity.createIntent(this).apply { startActivity(this) }
        }
    }
}
