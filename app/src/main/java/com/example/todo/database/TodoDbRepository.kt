package com.example.todo.database

import com.example.todo.ListItemModel
import com.example.todo.Repository
import kotlinx.coroutines.runBlocking
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
        todoDao.add(value.copyToEntity())
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

   /* private fun convertDateToString(date : LocalDate) : String {
        val formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")
        return date.format(formatter)
    }
    private fun convertStringToDate(stringValue : String) : LocalDate {
        val formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")
        return LocalDate.parse(stringValue, formatter)
    }*/
}

private fun ListItemEntity.copyToModel(): ListItemModel {
    return ListItemModel(id,
                        title,
                        description,
                        LocalDate.parse(date),
                        done)
}

private fun ListItemModel.copyToEntity(): ListItemEntity {
    return ListItemEntity(id,
                            title,
                            description,
                            date.format(DateTimeFormatter.ofPattern("dd LLLL yyyy")),
                            done)
}
