package ru.nct.mylovelyplace.navigation



sealed class Screen(val route: String) {

    object Home: Screen(ROUTE_HOME)

    object Event: Screen(ROUTE_EVENT)

    object Favorite: Screen(ROUTE_FAVOURITE)

    companion object {
        const val ROUTE_HOME = "home"
        const val ROUTE_EVENT = "events"
        const val ROUTE_FAVOURITE = "favourite"

    }
}