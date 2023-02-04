package ggj.dao

import ggj.*
import ggj.indicators.Indicators
import ggj.items.Item
import ggj.resources.NaturalResources
import ggj.resources.ResourceStorage
import ggj.resources.Upgrades
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

val resourceStorage = ResourceStorage()
val indicators = Indicators()
val naturalResources = NaturalResources()
val items = mutableListOf<Item>()
val upgrades = Upgrades(resourceStorage)



internal object Parcels : IntIdTable() {
    val user = integer("user_id").uniqueIndex().references(Users.id)

    val rsWoodQuantity = float("rs_wood_quantity")
    val rsWoodCapacity = float("rs_wood_capacity")

    val rsFruitsQuantity = float("rs_fruits_quantity")
    val rsFruitsCapacity = float("rs_fruits_capacity")

    val rsIronQuantity = float("rs_iron_quantity")
    val rsIronCapacity = float("rs_iron_capacity")

    val iBioVal = float("i_bio_val")
    val iBioEvo = float("i_bio_evo")

    val iAirVal = float("i_air_val")
    val iAirEvo = float("i_air_evo")

    val iSoilVal = float("i_soil_val")
    val iSoilEvo = float("i_soil_evo")

    val nrIron = float("nr_iron")
    val nrFruits = float("nr_fruits")
    val nrTrees = float("nr_trees")

    fun mapToObject(row: ResultRow): Parcel {
        var p_ = Parcel();
        p_.resourceStorage.wood.quantity = row[Parcels.rsWoodQuantity];
        p_.resourceStorage.wood.capacity = row[Parcels.rsWoodCapacity];
        p_.resourceStorage.fruits.quantity = row[Parcels.rsFruitsQuantity];
        p_.resourceStorage.fruits.capacity = row[Parcels.rsFruitsCapacity];
        p_.resourceStorage.iron.quantity = row[Parcels.rsIronQuantity];
        p_.resourceStorage.iron.capacity = row[Parcels.rsIronCapacity];
        p_.indicators.bio.value = row[Parcels.iBioVal];
        p_.indicators.bio.evolution = row[Parcels.iBioEvo];
        p_.indicators.air.value = row[Parcels.iAirVal];
        p_.indicators.air.evolution = row[Parcels.iAirEvo];
        p_.indicators.soil.value = row[Parcels.iSoilVal];
        p_.indicators.soil.evolution = row[Parcels.iSoilEvo];

        p_.naturalResources.iron = row[Parcels.nrIron];
        p_.naturalResources.fruits = row[Parcels.nrFruits];
        p_.naturalResources.trees = row[Parcels.nrTrees];

        return p_;
    }

}

class ParcelDao(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, ParcelDao>(Parcels) {

    }

    var user by Parcels.user
    var rsWoodQuantity by Parcels.rsWoodQuantity
    var rsWoodCapacity by Parcels.rsWoodCapacity

    var rsFruitsQuantity by Parcels.rsFruitsQuantity
    var rsFruitsCapacity by Parcels.rsFruitsCapacity

    var rsIronQuantity by Parcels.rsIronQuantity
    var rsIronCapacity by Parcels.rsIronCapacity

    var iBioVal by Parcels.iBioVal
    var iBioEvo by Parcels.iBioEvo

    var iAirVal by Parcels.iAirVal
    var iAirEvo by Parcels.iAirEvo

    var iSoilVal by Parcels.iSoilVal
    var iSoilEvo by Parcels.iSoilEvo

    var nrIron by Parcels.nrIron
    var nrFruits by Parcels.nrFruits
    var nrTrees by Parcels.nrTrees


}

