package ggj23.dao

import ggj.User
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow

internal object Users : IdTable<String>() {
    override val id: Column<EntityID<String>> = varchar("id", 4).entityId()
    val name = varchar("name", 50)

    fun toUser(it: ResultRow): User {
        return User(it[id].value, it[name])
    }
}

//https://github.com/JetBrains/Exposed/issues/497
internal class UserDao(id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, UserDao>(Users) {
        fun createFrom(user: User): UserDao {
            return UserDao.new(user.userId) {
                this.name = user.userName;
            }
        }
    }

    private var name by Users.name
}