package ru.nlct.mylovelyplace.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.nlct.mylovelyplace.DatabaseConst.CONTENT
import ru.nlct.mylovelyplace.DatabaseConst.ID
import ru.nlct.mylovelyplace.DatabaseConst.IMG
import ru.nlct.mylovelyplace.DatabaseConst.LAT
import ru.nlct.mylovelyplace.DatabaseConst.LON
import ru.nlct.mylovelyplace.DatabaseConst.PLACE
import ru.nlct.mylovelyplace.DatabaseConst.TITLE

/**
 * Place entity - сущность базы данных
 *
 * @property id - идентификатор места
 * @property title - наименование
 * @property content - описание
 * @property imageLink - ссылка на изображение
 * @property latitude - широта
 * @property longitude - долгота
 * @author Marianna Sabanchieva
 */

@Entity(tableName = PLACE)
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID) val id: Long = 0,
    @ColumnInfo(name = TITLE) val title: String = "",
    @ColumnInfo(name = CONTENT) val content: String? = "",
    @ColumnInfo(name = IMG) val imageLink: String? = "",
    @ColumnInfo(name = LAT) var latitude: Double? = 0.0,
    @ColumnInfo(name = LON) var longitude: Double? = 0.0
)