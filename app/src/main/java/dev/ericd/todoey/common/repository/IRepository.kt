package dev.ericd.todoey.common.repository

interface IRepository<ENTITY_ID, ENTITY> {

    fun getAll(): List<ENTITY>

    fun insert(entity: ENTITY)

}