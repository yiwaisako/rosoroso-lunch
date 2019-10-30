package jp.co.yiwaisako.rosoroso_lunch.presentation.restaurantList

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.co.yiwaisako.rosoroso_lunch.R
import jp.co.yiwaisako.rosoroso_lunch.databinding.ActivityRestaurantListBinding
import jp.co.yiwaisako.rosoroso_lunch.domain.model.Restaurant
import jp.co.yiwaisako.rosoroso_lunch.presentation.restaurantRegister.RestaurantRegisterActivity
import jp.co.yiwaisako.rosoroso_lunch.presentation.reviewList.ReviewListActivity
import jp.co.yiwaisako.rosoroso_lunch.util.ext.contentViewBinding
import jp.co.yiwaisako.rosoroso_lunch.util.ext.toGone
import timber.log.Timber


class RestaurantListActivity : AppCompatActivity(), RestaurantListContract.View {

    companion object {
        private const val EXTRA_STATION_KEY = "stationKey"
        private const val REQUEST_CODE_RESTAURANT_REGISTER = 1000

        @JvmStatic
        fun createIntent(context: Context, stationKey: String) =
            Intent(context, RestaurantListActivity::class.java).apply {
                putExtra(EXTRA_STATION_KEY, stationKey)
            }
    }

    private var stationKey: String = ""
    private val binding: ActivityRestaurantListBinding by contentViewBinding(
        R.layout.activity_restaurant_list
    )
    private lateinit var presenter: RestaurantListContract.Presenter
    private val controller by lazy { RestaurantListController(presenter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = RestaurantListPresenter(this)

        stationKey = savedInstanceState?.getString(EXTRA_STATION_KEY, "")
            ?: intent.getStringExtra(EXTRA_STATION_KEY)

        binding.apply {
            recyclerView.setController(controller)
        }

        binding.fab.setOnClickListener {
            RestaurantRegisterActivity.createIntent(this, stationKey).apply {
                startActivityForResult(this, REQUEST_CODE_RESTAURANT_REGISTER)
            }
        }

        presenter.onCreate(stationKey)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data ?: return
        if (resultCode != Activity.RESULT_OK) return
        when (requestCode) {
            REQUEST_CODE_RESTAURANT_REGISTER -> {
                presenter.onCreate(stationKey)
            }
        }
    }

    override fun setup(data: List<Restaurant>) {
        Timber.d("setup")
        binding.progressbar.toGone()
        controller.setData(data)
    }

    override fun moveToReviewList(documentId: String) {
        ReviewListActivity.createIntent(this, documentId).apply {
            startActivity(this)
        }
    }
}
