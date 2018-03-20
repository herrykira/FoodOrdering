package com.example.kinhangpoon.foodordering.utility

import com.example.kinhangpoon.foodordering.model.FoodItem
import com.example.kinhangpoon.foodordering.model.OrderDetailItem

/**
 * Created by hefen on 3/18/2018.
 */
object FoodDescription {
    var foodListDelhi: List<FoodItem>? = null
    var foodListBanglore: List<FoodItem>? = null
    var recordList: List<OrderDetailItem>? = null
    var currentCity: String? = null
    var confirmNum: String? = null
}