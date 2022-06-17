package com.example.e_commerceapp.ui.reviews.model

data class Review(val name:String,val description:String, val rating:Float)

fun getAllReviews():List<Review>{
    val reviews = listOf(Review("Asma","It looks good I really like it.",5f),
                         Review("Nour","So Cute!! easily my new fav and very comfortable. ",5f),
                         Review("Nada","They are incredibly cute. I am very impressed. I love them.",5f),
                         Review("Ali","It looks better in real life. thanks seller",5f),
                         Review("Logy","I love them so mush . Alittle bit larger but thats okay",4f))

    return reviews
}

fun getOnlyTwoReviews():List<Review>{
    val reviews = listOf(Review("Asma","It looks good I really like it.",5f),
        Review("Nour","So Cute!! easily my new fav and very comfortable.",5f))

    return reviews
}
