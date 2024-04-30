package cz.mandr.dartscounter.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow


@Dao
interface GameDao{

    @Insert
    fun insertGame(game: GameDTO)

    @Query("DELETE FROM games WHERE gameID = :gameId")
    fun deleteGameById(gameId: Int)

    @Query("DELETE FROM games")
    fun deleteAllGames()

    @Query("SELECT * FROM games WHERE gameID = :gameId")
    fun getGameById(gameId: Int) : Flow<GameDTO>

    @Query("SELECT * FROM games ORDER BY gameID DESC")
    fun getAllGames() : Flow<List<GameDTO>>

    @Query("SELECT MAX(gameID) FROM games")
    fun getMaxId() : Flow<Int>

    @Query("SELECT COUNT(*) FROM games")
    fun getRowsCount() : Flow<Int>

}

@Database(entities = [GameDTO::class], version = 1)
abstract class MyRoomDatabase : RoomDatabase(){
    abstract val gameDao: GameDao
}

private lateinit var INSTANCE: MyRoomDatabase

fun getDatabase(context: Context) : MyRoomDatabase {
    synchronized(MyRoomDatabase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                MyRoomDatabase::class.java,
                "my_room_database").build()
        }
    }
    return INSTANCE
}