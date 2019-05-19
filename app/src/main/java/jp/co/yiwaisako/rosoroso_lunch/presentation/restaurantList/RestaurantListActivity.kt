package jp.co.yiwaisako.rosoroso_lunch.presentation.restaurantList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.co.yiwaisako.rosoroso_lunch.R
import jp.co.yiwaisako.rosoroso_lunch.databinding.ActivityRestaurantListBinding
import jp.co.yiwaisako.rosoroso_lunch.domain.model.Restaurant
import jp.co.yiwaisako.rosoroso_lunch.presentation.reviewList.ReviewListActivity
import jp.co.yiwaisako.rosoroso_lunch.util.ext.contentViewBinding
import jp.co.yiwaisako.rosoroso_lunch.util.ext.toGone
import timber.log.Timber


class RestaurantListActivity : AppCompatActivity(), RestaurantListContract.View {

    companion object {
        private const val EXTRA_STATION_DOCUMENT = "station_document"

        @JvmStatic
        fun createIntent(context: Context, stationDocument: String) =
            Intent(context, RestaurantListActivity::class.java).apply {
                putExtra(EXTRA_STATION_DOCUMENT, stationDocument)
            }
    }

    private var stationDocumentId: String = ""
    private val binding: ActivityRestaurantListBinding by contentViewBinding(R.layout.activity_restaurant_list)
    private lateinit var presenter: RestaurantListContract.Presenter
    private val controller by lazy { RestaurantListController(presenter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = RestaurantListPresenter(this)

        stationDocumentId = savedInstanceState?.getString(EXTRA_STATION_DOCUMENT, "")
            ?: intent.getStringExtra(EXTRA_STATION_DOCUMENT)

        binding.apply {
            recyclerView.setController(controller)
        }

        presenter.onCreate(stationDocumentId)
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
