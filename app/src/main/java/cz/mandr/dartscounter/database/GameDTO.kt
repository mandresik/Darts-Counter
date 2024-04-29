package cz.mandr.dartscounter.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameDTO(
    @PrimaryKey
    val gameID: Int,
    val gameName: String,
    val count: Int,
    val firstToPlay: Int,
    val players: String,
    val score: String
)

fun GameDTO.mapToGame(): Game {
    return Game(
        gameID = this.gameID,
        gameName = this.gameName,
        count = this.count,
        firstToPlay = this.firstToPlay,
        players = this.players.split(", "),
        score = this.score.split(", ")
    )
}