package com.ken.roomdatabaseproject

class Repo(private val dao: Dao) {

     fun insert(note: Note) {
        dao.insert(note)
    }

     fun delete(note: Note) {
        dao.delete(note)
    }

     fun update(note: Note) = dao.update(note)
     fun getAllNotes() = dao.getAll()

}