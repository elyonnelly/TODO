package com.example.todo.database

import com.example.todo.ListItemModel
import com.example.todo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TodoDbRepository(private val todoDao : TodoDao) : Repository<ListItemModel> {
    override val size: Int
        get() = todoDao.getSize()

    override fun get(id: Int): ListItemModel {
        val itemEntity = todoDao.getById(id)
        return itemEntity.copyToModel()
    }

    override fun add(value: ListItemModel) {
        todoDao.add(value.copy(id = size).copyToEntity())
    }

    override fun update(value: ListItemModel) {
        todoDao.update(value.copyToEntity())
    }

    override fun remove(id: Int) {
        todoDao.remove(id)
    }

    override fun getAll(): List<ListItemModel>  {
        return todoDao.getAll().map { entity -> entity.copyToModel() }
    }

}

private fun ListItemEntity.copyToModel(): ListItemModel {
    return ListItemModel(id,
                        title,
                        description,
                        date,
                        done)
}

private fun ListItemModel.copyToEntity(): ListItemEntity {
    return ListItemEntity(id,
                            title,
                            description,
                            date,
                            done)
}
