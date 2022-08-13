interface CrudService<E> {
    fun add(element: E): E

    fun delete(id: Int): Boolean

    fun edit(element: E): Boolean

    fun getByID(id: Int): E

    fun restore(id: Int): Boolean
}