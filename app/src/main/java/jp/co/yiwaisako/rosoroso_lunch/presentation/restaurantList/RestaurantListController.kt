package jp.co.yiwaisako.rosoroso_lunch.presentation.restaurantList

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import jp.co.yiwaisako.rosoroso_lunch.domain.model.Restaurant
import jp.co.yiwaisako.rosoroso_lunch.empty
import jp.co.yiwaisako.rosoroso_lunch.restaurant
import jp.co.yiwaisako.rosoroso_lunch.space
import timber.log.Timber

class RestaurantListController(
    private val presenter: RestaurantListContract.Presenter
) : TypedEpoxyController<List<Restaurant>>() {

    override fun buildModels(data: List<Restaurant>) {
        when (data.isEmpty()) {
            true -> {
                empty {
                    id("itemEmpty")
                }
            }
            false -> {
                data.forEach { restaurant ->
                    Timber.d(restaurant.name)
                    restaurant {
                        id("restaurant $restaurant")
                        restaurantName(restaurant.name)
                        onClick(View.OnClickListener {
                            presenter.onClickRestaurant(restaurant.id)
                        })
                    }
                }
            }
        }
    }

    private fun addSpace(height: Int = 16) {
        space {
            id(modelCountBuiltSoFar)
            widthDp(0)
            heightDp(height)
        }
    }
}