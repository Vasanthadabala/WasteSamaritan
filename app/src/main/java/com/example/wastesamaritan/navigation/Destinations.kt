package com.example.wastesamaritan.navigation

interface Destinations
{
    val route:String
}
object Signin: Destinations {
    override val route="Signin"
}
object Home: Destinations {
    override val route = "Home"
}
object AboutUs: Destinations {
    override val route="About Us"
}
object AboutProject: Destinations {
    override val route="About Project"
}
object MyRatings: Destinations {
    override val route="My Ratings"
}
object Sync: Destinations {
    override val route="Sync"
}
object Complaints: Destinations {
    override val route="Complaints"
}
