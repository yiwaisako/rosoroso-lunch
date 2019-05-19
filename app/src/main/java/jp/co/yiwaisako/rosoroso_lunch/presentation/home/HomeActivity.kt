package jp.co.yiwaisako.rosoroso_lunch.presentation.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.co.yiwaisako.rosoroso_lunch.R
import jp.co.yiwaisako.rosoroso_lunch.databinding.ActivityHomeBinding
import jp.co.yiwaisako.rosoroso_lunch.presentation.restaurantList.RestaurantListActivity
import jp.co.yiwaisako.rosoroso_lunch.util.ext.contentViewBinding


class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by contentViewBinding(R.layout.activity_home)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setup()
    }

    private fun setup() {
        binding.nakaMeguro.setOnClickListener {
            moveToRestaurantList("/stations/naka-meguro")
        }
        binding.shibuya.setOnClickListener {
            moveToRestaurantList("/stations/shibuya")
        }
        binding.shinsen.setOnClickListener {
            moveToRestaurantList("/stations/shinsen")
        }
        binding.ikejirioohash.setOnClickListener {
            moveToRestaurantList("/stations/ikejiri-ohashi")
        }
    }

    private fun moveToRestaurantList(stationDocument: String) {
        RestaurantListActivity.createIntent(this, stationDocument).apply {
            startActivity(this)
        }
    }
}
