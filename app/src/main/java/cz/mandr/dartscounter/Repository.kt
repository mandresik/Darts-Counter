package cz.mandr.dartscounter

import cz.mandr.dartscounter.database.Game
import cz.mandr.dartscounter.database.GameDTO
import cz.mandr.dartscounter.database.MyRoomDatabase
import cz.mandr.dartscounter.database.mapToGame
import cz.mandr.dartscounter.database.mapToGameDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class Repository(private val database: MyRoomDatabase) {

    suspend fun insertGame(game: Game){
        withContext(Dispatchers.IO){
            database.gameDao.insertGame(game.mapToGameDTO())
        }
    }

    suspend fun deleteById(gameId: Int){
        withContext(Dispatchers.IO){
            database.gameDao.deleteGameById(gameId)
        }
    }

    suspend fun deleteAllGames(){
        withContext(Dispatchers.IO){
            database.gameDao.deleteAllGames()
        }
    }

    fun getGameById(gameId: Int) : Flow<Game> {
        return database.gameDao.getGameById(gameId).map { it.mapToGame() }
    }

    fun getAllGames() : Flow<List<Game>> {
        return database.gameDao.getAllGames().map {
            gameDTOList -> gameDTOList.map { it.mapToGame() }
        }
    }

    fun getMaxId() : Flow<Int> {
        return database.gameDao.getMaxId()
    }

    fun getRowsCount() : Flow<Int> {
        return database.gameDao.getRowsCount()
    }

}