import java.io.Serializable

data class Article(
    val title: String,
    val description: String,
    val mediaUrl: String,
    val author: String
) : Serializable
