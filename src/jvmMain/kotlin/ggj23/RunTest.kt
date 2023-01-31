package ggj23

import ggj.User
import ggj23.dao.UserDao
import ggj23.dao.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {

//    https://github.com/JetBrains/Exposed
//    https://www.baeldung.com/kotlin/exposed-persistence
    Database.connect("jdbc:postgresql://localhost:5432/test_db", "org.postgresql.Driver", "postgres", "password")
    setupDb()
    insertDummyData()
    wipeAll()

}

private fun setupDb() {
    transaction { SchemaUtils.create(Users) }
}

private fun insertDummyData() {
    transaction {
        val tibo = User(userName = "Thibaud")
        println(tibo)

        UserDao.createFrom(tibo)
        Users.selectAll().forEach {
            println(it)
            val user = Users.toUser(it)
            println(user)
        }
    }
}

private fun wipeAll() {
    transaction {
        Users.deleteAll()
    }
}
//https://www.optimadata.nl/blogs/1/n8dyr5-how-to-run-postgres-on-docker-part-1