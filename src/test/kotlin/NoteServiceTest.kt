import org.junit.Test

import org.junit.Assert.*

class ServiceTest {

    @Test
    fun delete_notDeleteIDNotEquals() {
        val service = NoteService()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val result = service.delete(5)
        assertFalse(result)
    }

    @Test
    fun delete_notDeleteIsDeletedTrue() {
        val service = NoteService()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = true)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val result = service.delete(1)
        assertFalse(result)
    }

    @Test
    fun delete_itIsOK() {
        val service = NoteService()
        val comment = mutableListOf<Comment>()
        val note1 = Note(id = 1, title = "", text = "", isDeleted = false)
        val note2 = Note(id = 3, title = "", text = "", isDeleted = false)
        service.add(note1)
        service.add(note2)
        val comment1 = Comment(id = 1, message = "aa", isDeleted = false)
        val comment2 = Comment(id = 1, message = "ccz", isDeleted = false)
        comment.add(comment1)
        comment.add(comment2)
        val result = service.delete(2)
        assertTrue(result)
    }

}