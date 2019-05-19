package jp.co.yiwaisako.rosoroso_lunch.presentation.restaurantList

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import jp.co.yiwaisako.rosoroso_lunch.domain.model.Restaurant
import jp.co.yiwaisako.rosoroso_lunch.itemEmpty
import jp.co.yiwaisako.rosoroso_lunch.itemRestaurant
import jp.co.yiwaisako.rosoroso_lunch.itemSpace
import timber.log.Timber

class RestaurantListController(
    private val presenter: RestaurantListContract.Presenter
) : TypedEpoxyController<List<Restaurant>>() {

    override fun buildModels(data: List<Restaurant>) {
        when (data.isEmpty()) {
            true -> {
                itemEmpty {
                    id("itemEmpty")
                }
            }
            false -> {
                data.forEach { restaurant ->
                    Timber.d(restaurant.name)
                    itemRestaurant {
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
        itemSpace {
            id(modelCountBuiltSoFar)
            widthDp(0)
            heightDp(height)
        }
    }
}