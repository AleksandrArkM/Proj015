class NoteService: CrudService<Note> {
    val elementsList = mutableListOf<Note>()
    private val comment = mutableListOf<Comment>()
    private var nextID = 1

    override fun add(element: Note): Note {
        val newNote = element.copy(id = nextID++)
        elementsList.add(newNote)
        return elementsList.last()
    }

    override fun delete(id: Int): Boolean {
        var change = false
        for ((index, value) in elementsList.withIndex()) {
            if (id == value.id && !value.isDeleted) {
                elementsList[index] = value.copy(isDeleted = true)
                if (comment.isNotEmpty()) {
                    val commentIsDelete = comment.get(index)
                    comment[index] = commentIsDelete.copy(isDeleted = true)
                }
                change = true
                break
            }
        }
        return change
    }

    override fun edit(element: Note): Boolean {
        var change = false
        for ((index, value) in elementsList.withIndex()) {
            if (element.id == value.id && !value.isDeleted) {
                elementsList[index] = value.copy(
                    title = element.title,
                    text = element.text
                )
                change = true
                break
            }
        }
        return change
    }

    override fun getByID(id: Int): Note {
        val listByID = mutableListOf<Note>()
        for (note in elementsList) {
            if (id == note.id && !note.isDeleted)
                listByID.add(note)
        }
        return if (listByID.isNotEmpty()) listByID.last() else throw CommentExeption("Can't find object for this Id")
    }

    override fun restore(id: Int): Boolean {
        throw CommentExeption("Can't restore object")
    }

}