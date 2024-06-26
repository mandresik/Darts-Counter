package cz.mandr.dartscounter.database

data class Game(
    val gameID: Int,
    val gameName: String,
    val count: Int,
    val firstToPlay: Int,
    val players: List<String>,
    val score: List<String>
){
    fun playersString() : String{
        var ret: String = ""
        for(i in 0 ..< count){
            ret += players[i] + ": " + score[i] + "\n"
        }
        return ret
    }
}

fun Game.mapToGameDTO() : GameDTO {
    return GameDTO(
        gameID = this.gameID,
        gameName = this.gameName,
        count = this.count,
        firstToPlay = this.firstToPlay,
        players = this.players.joinToString(", "),
        score = this.score.joinToString(", ")
    )
}