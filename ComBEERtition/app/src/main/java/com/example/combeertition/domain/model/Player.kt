package com.example.combeertition.domain.model

import androidx.compose.ui.graphics.Color
import java.time.ZonedDateTime

@JvmInline
value class PlayerId(val value: String)


class Player private constructor(
    val id: PlayerId,
    val name: String,
    val icon: Int,
    val color: Color,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: PlayerId,
            name: String,
            icon: Int,
            color: Color
        ): Player {
            val now = ZonedDateTime.now()
            return Player(id, name, icon, color, now, now, now)
        }
    }
}


