data class Note(
    val id: Int,
    val title: String,
    val text: String,
    val isDeleted: Boolean
) {
}
data class Comment(
    val id: Int,
    val message: String,
    val isDeleted: Boolean
) {
}
