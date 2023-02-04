package ggj.dao

import ggj.UserMe
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

internal object Parcels : IntIdTable() {
}

class ParcelDao(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, ParcelDao>(Parcels) {
        fun createFrom(user: UserMe): UserDao {
            return UserDao.new(user.publicUser.userId) {

            }
        }
    }

}