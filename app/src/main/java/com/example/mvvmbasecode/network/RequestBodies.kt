package com.example.mvvmbasecode.network

object RequestBodies {

    data class LoginBody(
        val email: String,
        val password: String,
        val device_token: String,
        val device_type: String
    )

    data class LogoutBody(
        val device_token: String,
    )

    data class SocialLoginBody(
        val email: String,
        val provider: String,
        val social_token: String,
        val device_token: String,
        val device_type: String,
        val user_type: String
    )

    data class ForgotPasswordBody(
        val email: String
    )

    data class SignUpBody(
        val name: String,
        val email: String,
        val password: String,
        val user_type: String,
        val device_token: String,
        val device_type: String
    )

    data class ChangePasswordBody(
        val old_password: String,
        val new_password: String,
        val confirm_password: String
    )

    data class GetProductListBody(
        val supplier_id: String,
        val latitude: String,
        val longitude: String
    )

    data class GetDishListBody(
        val chef_id: String,
        val latitude: String,
        val longitude: String
    )

    data class GetConsumerDashboard(
        val latitude: String,
        val longitude: String,
        val type: String
    )

    data class GetNearByProductSuppliers(
        val latitude: String,
        val longitude: String
    )

    data class DishOrderPayment(
        val amount: String,
        val currency: String,
        val product_id: String,
        val payment_method_id: String
    )

    data class DishDetailPayment(
        val amount: String,
        val currency: String,
        val dish_id: String,
        val date: String,
        val time: String,
        val location: String,
        val payment_method_id: String
    )

    data class settingNotificationBody(
        val notification_enable: String,
    )
}