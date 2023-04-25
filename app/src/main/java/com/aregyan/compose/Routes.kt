package com.aregyan.compose

sealed class Routes(val route: String){
    object Login : Routes("Login")
    object SignUp : Routes("SignUp")
    object ForgotPassword : Routes("ForgotPassword")
    object User : Routes("user")
    object Detail : Routes("detail")
}
