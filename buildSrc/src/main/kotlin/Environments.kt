object Environments {

    data class Environment(
        val baseURL: String = "",
    )

    val release = Environment(
        baseURL = "https://eztanda.com/",
    )
    val debug = Environment(
        baseURL = "https://eztanda.com/",
    )

}