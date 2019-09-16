package jp.co.yiwaisako.rosoroso_lunch.presentation.restaurantRegister

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import jp.co.yiwaisako.rosoroso_lunch.R
import jp.co.yiwaisako.rosoroso_lunch.databinding.ActivityRestaurantRegisterBinding
import jp.co.yiwaisako.rosoroso_lunch.util.ext.contentViewBinding


class RestaurantRegisterActivity : AppCompatActivity(), RestaurantRegisterContract.View {

    companion object {
        private const val EXTRA_STATION_KEY = "stationKey"

        @JvmStatic
        fun createIntent(context: Context, stationKey: String) =
            Intent(context, RestaurantRegisterActivity::class.java).apply {
                putExtra(EXTRA_STATION_KEY, stationKey)
            }
    }

    private val binding: ActivityRestaurantRegisterBinding by contentViewBinding(R.layout.activity_restaurant_register)
    private lateinit var presenter: RestaurantRegisterContract.Presenter
    private var stationKey = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = RestaurantRegisterPresenter(this)

        stationKey = savedInstanceState?.getString(EXTRA_STATION_KEY, "")
            ?: intent.getStringExtra(EXTRA_STATION_KEY)

        setup()
    }

    override fun close() {
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun showToast(messsage: String) {
        Toast.makeText(this, messsage, Toast.LENGTH_LONG).show()
    }

    private fun setup() {
        binding.registerButton.setOnClickListener {
            val name = binding.restaurant.text.toString()
            val url = binding.url.text.toString()
            presenter.onClickRegister(name, url, stationKey)
        }
    }
}
