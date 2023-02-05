package ggj.task

import ggj.dao.ParcelDao
import org.jetbrains.exposed.sql.transactions.transaction

class UpdateWorldJob {

    fun updateWorld() {
        transaction {
            ParcelDao.all().forEach {
                val parcel = it.toParcel()
                parcel.produceResources()
                it.applyParcel(parcel)
            }
        }
    }
}