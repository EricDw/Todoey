package dev.ericd.todoey.common.repository

interface IRepository<ENTITY_ID, ENTITY> {

    suspend fun insert(entity: ENTITY)

    suspend fun getAll(): List<ENTITY>

    suspend fun delete(entity: ENTITY)

}