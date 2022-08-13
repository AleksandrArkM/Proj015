class CommentService : CrudService<Comment> {
    val elementsList = mutableListOf<Comment>()
    private val note = NoteService()

    override fun add(element: Comment): Comment {
        for ((index, value) in note.elementsList.withIndex()) {
            when {
                value.id == element.id && !value.isDeleted && !element.isDeleted -> {
                    elementsList.add(element)
                    break
                }
                index < note.elementsList.size - 1 -> continue
                else -> break
            }
        }
        return if (elementsList.isNotEmpty()) elementsList.last() else throw CommentExeption("Can't add comment for note")
    }

    override fun delete(id: Int): Boolean {
        var change = false
        for ((index, value) in elementsList.withIndex()) {
            when {
                id != value.id && (index < elementsList.size - 1) -> continue
                id == value.id && !value.isDeleted -> {
                    elementsList[index] = value.copy(isDeleted = true)
                    change = true
                    break
                }
                else -> throw CommentExeption("Can't delete cause this comment is already deleted")
            }
        }
        return change
    }

    override fun edit(element: Comment): Boolean {
        var change = false
        for ((index, value) in elementsList.withIndex()) {
            when {
                element.id != value.id && (index < elementsList.size - 1) -> continue
                element.id == value.id && !value.isDeleted -> {
                    elementsList[index] = element.copy(message = element.message)
                    change = true
                    break
                }
                else -> throw CommentExeption("Can't edit cause this comment is already deleted")
            }
        }
        return change
    }

    override fun getByID(id: Int): Comment {
        val listByID = mutableListOf<Comment>()
        for (comment in elementsList) {
            if (id == comment.id && !comment.isDeleted)
                listByID.add(comment)
        }
        return if (listByID.isNotEmpty()) listByID.last() else throw CommentExeption("Can't find comment for this Id")
    }

    override fun restore(id: Int): Boolean {
        var change = false
        for ((index, value) in elementsList.withIndex()) {
            when {
                id != value.id && (index < elementsList.size - 1) -> continue
                id == value.id && value.isDeleted -> {
                    elementsList[index] = value.copy(isDeleted = false)
                    change = true
                    break
                }
                else -> throw CommentExeption("Can't restore deleted comment")
            }
        }
        return change
    }
}