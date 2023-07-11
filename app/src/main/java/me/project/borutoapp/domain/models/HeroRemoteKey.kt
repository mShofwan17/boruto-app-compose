package me.project.borutoapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.project.borutoapp.utils.Constant

@Entity(Constant.DatabaseConst.TB_HERO_REMOTE_KEY)
data class HeroRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
