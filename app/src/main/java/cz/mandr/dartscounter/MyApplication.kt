package cz.mandr.dartscounter

import android.app.Application
import cz.mandr.dartscounter.database.getDatabase

class MyApplication : Application() {

    val repository: Repository by lazy {
        Repository(getDatabase(this))
    }

    override fun onCreate(){
        super.onCreate()
    }

}