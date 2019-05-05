package jp.co.yiwaisako.rosoroso_lunch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_station_list.*


class ReviewPostingActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun createIntent(context: Context) =
            Intent(context, ReviewPostingActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_posting)

        nakameguro.setOnClickListener {
            RestaurantListActivity.createIntent(this).apply { startActivity(this) }
        }
    }
}
