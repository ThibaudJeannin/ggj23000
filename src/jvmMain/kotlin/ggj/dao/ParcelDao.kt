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

}

class ParcelDao(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, ParcelDao>(Parcels)

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

    fun toParcel(): Parcel {
        var parcel = Parcel();
        parcel.resourceStorage.wood.quantity = rsWoodQuantity;
        parcel.resourceStorage.wood.capacity = rsWoodCapacity;
        parcel.resourceStorage.fruits.quantity = rsFruitsQuantity;
        parcel.resourceStorage.fruits.capacity = rsFruitsCapacity;
        parcel.resourceStorage.iron.quantity = rsIronQuantity;
        parcel.resourceStorage.iron.capacity = rsIronCapacity;
        parcel.indicators.bio.value = iBioVal;
        parcel.indicators.bio.evolution = iBioEvo;
        parcel.indicators.air.value = iAirVal;
        parcel.indicators.air.evolution = iAirEvo;
        parcel.indicators.soil.value = iSoilVal;
        parcel.indicators.soil.evolution = iSoilEvo;

        parcel.naturalResources.iron = nrIron;
        parcel.naturalResources.fruits = nrFruits;
        parcel.naturalResources.trees = nrTrees;
        print("MAIS PUTAIN : ${parcel.naturalResources}\n")
        return parcel;
    }
    fun applyParcel(parcel: Parcel) {
        rsWoodQuantity = parcel.resourceStorage.wood.quantity;
        rsWoodCapacity = parcel.resourceStorage.wood.capacity;
        rsFruitsQuantity = parcel.resourceStorage.fruits.quantity;
        rsFruitsCapacity = parcel.resourceStorage.fruits.capacity;
        rsIronQuantity = parcel.resourceStorage.iron.quantity;
        rsIronCapacity = parcel.resourceStorage.iron.capacity;
        iBioVal = parcel.indicators.bio.value;
        iBioEvo = parcel.indicators.bio.evolution;
        iAirVal = parcel.indicators.air.value;
        iAirEvo = parcel.indicators.air.evolution;
        iSoilVal = parcel.indicators.soil.value;
        iSoilEvo = parcel.indicators.soil.evolution;

        nrIron = parcel.naturalResources.iron;
        nrFruits = parcel.naturalResources.fruits;
        nrTrees = parcel.naturalResources.trees;
    }
}

