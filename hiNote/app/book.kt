data class book(
    val copyright: String,
    val imageUrl: String,
    val item: List<Item>,
    val itemsPerPage: Int,
    val language: String,
    val link: String,
    val maxResults: Int,
    val pubDate: String,
    val queryType: String,
    val returnCode: String,
    val returnMessage: String,
    val searchCategoryId: String,
    val searchCategoryName: String,
    val startIndex: Int,
    val title: String,
    val totalResults: Int
)