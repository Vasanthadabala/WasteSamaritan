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
    override val route = "About Us"
}
object AboutProject: Destinations {
    override val route = "About Project"
}
object MyRatings: Destinations {
    override val route = "My Ratings"
}
object Complaints: Destinations {
    override val route = "Complaints"
}
//object Sync: Destinations {
//    override val route = "Sync"
//}
object Profile: Destinations {
    override val route = "Profile"
}
object IndividualHouse: Destinations {
    override val route = "IndividualHouse"
}
object Segregated: Destinations {
    override val route = "Segregated"
    const val itemID = "itemId"
}
object NotSegregated: Destinations {
    override val route = "NotSegregated"
    const val itemID = "itemId"
}
object Data: Destinations {
    override val route = "Data"
}

