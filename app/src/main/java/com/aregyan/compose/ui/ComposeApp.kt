package com.aregyan.compose.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aregyan.compose.Routes
import com.aregyan.compose.ui.ForgotPassword.ForgotPasswordScreen
import com.aregyan.compose.ui.details.DetailsScreen
import com.aregyan.compose.ui.login.LoginScreen
import com.aregyan.compose.ui.signup.SignUpScreen
import com.aregyan.compose.ui.users.UsersScreen

@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route) {
            LoginScreen(navHostController = navController)
        }

        composable(Routes.SignUp.route) {
            SignUpScreen(navHostController = navController)
        }

        composable(Routes.ForgotPassword.route) {
            ForgotPasswordScreen(navHostController = navController)
        }

        composable(Route.USER) { backStackEntry ->
            UsersScreen(
                onUserClick = { username ->
                    // In order to discard duplicated navigation events, we check the Lifecycle
                    if (backStackEntry.getLifecycle().currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Route.DETAIL}/$username")
                    }
                }
            )
        }
        composable(
            route = "${Route.DETAIL}/{${Argument.USERNAME}}",
            arguments = listOf(
                navArgument(Argument.USERNAME) {
                    type = NavType.StringType
                }
            ),
        ) {
            DetailsScreen()
        }
    }
}

object Route {
    const val USER = "user"
    const val DETAIL = "detail"
}

object Argument {
    const val USERNAME = "username"
}