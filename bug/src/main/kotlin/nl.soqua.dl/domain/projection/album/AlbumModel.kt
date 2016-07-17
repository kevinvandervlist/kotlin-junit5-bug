package nl.soqua.dl.domain.projection.album

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

internal object AlbumProjectionTable : IntIdTable() {
    val identity = varchar("identity", 128).primaryKey()
    val data = text("data")
}

internal class AlbumProjectionModel(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AlbumProjectionModel>(AlbumProjectionTable)

    var identity by AlbumProjectionTable.identity
    var data by AlbumProjectionTable.data
}

data class Multimedia(val name: String, val identity: String) {
}

data class Album(val identity: String, val name: String, val startDate: String, val endDate: String, val media: List<Multimedia>, val version: Int) {
}
