package ggj.dao

import ggj.UserMe
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

internal object Users : IntIdTable() {
    val name = varchar("name", 50)
    val tag = varchar("user_tag",4)
}

//https://github.com/JetBrains/Exposed/issues/497
class UserDao(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, UserDao>(Users) {
        fun createFrom(user: UserMe): UserDao {
            return UserDao.new(user.publicUser.userId) {
                this.name = user.publicUser.userName
                this.tag = user.userTag
            }
        }
    }

    internal var name by Users.name
    internal var tag by Users.tag
}