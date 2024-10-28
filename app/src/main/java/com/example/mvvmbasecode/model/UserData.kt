package com.example.mvvmbasecode.model

import com.example.mvvmbasecode.database.userdata.UserDataEntity

data class UserDataResponse(
    var users: ArrayList<UserDataEntity>,
    var total: Int,
    var skip: Int,
    var limit: Int,
)

data class SubCategories(
    var sub_category_id: String,
    var service_ids: String,
    var regular_prices: String,
    var parent_category_id: String,
    var sub_category_name: String,
    var sub_category_photo: String,
    var sub_category_active_status: String,
    var products:ArrayList<Products> = arrayListOf()
)
data class Products(
    var order_in_out: String,
    var user_id: String,
    var user_full_name: String,
    var user_relation: String,
    var product_id: String,
    var sub_category_id: String,
    var product_name: String,
    var tag_code: String,
    var group_name: String,
    var city_name: String,
    var state_id: String,
    var city_id: String,
    var state_name: String,
    var product_photo: String,
    var product_active_status: String,
    var in_out: String,
)
data class Services(
    var service_id: String,
    var service_name: String,
    var service_desc: String,
    var indexing: String,
    var included_service: String,
    var included_service_name: String,
    var service_base_price: String,
    var service_icon: String,
    var service_active_status: String,
)
