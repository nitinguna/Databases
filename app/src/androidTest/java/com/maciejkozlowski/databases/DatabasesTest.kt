package com.maciejkozlowski.databases

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.maciejkozlowski.databases.greendao.CitiesLoaderDao
import com.maciejkozlowski.databases.objectbox.CitiesLoaderBox
import com.maciejkozlowski.databases.objectbox.CityBox
import com.maciejkozlowski.databases.realm.CitiesLoaderRealm
import com.maciejkozlowski.databases.results.ResultSet
import com.maciejkozlowski.databases.results.Saver
import com.maciejkozlowski.databases.results.TestConstants
import com.maciejkozlowski.databases.room.CitiesLoaderRoom
import com.maciejkozlowski.databases.sqlite.CitiesDatabase
import com.maciejkozlowski.databases.sqlite.CitiesLoaderSql
import io.realm.Realm
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DatabasesTest {

    private val resultSet = ResultSet()

    private val citiesLoaderSql = CitiesLoaderSql()

    private val citiesLoaderDao = CitiesLoaderDao()
    private val citiesLoaderBox = CitiesLoaderBox()


    @org.junit.Test
    fun testDatabasesRoom() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        val application = context as MyApplication
        val citiesLoaderRoom = CitiesLoaderRoom()

        val cityRoomDao = application.room.getCityRoomDao()

        citiesLoaderRoom.creatdb(context, resultSet, cityRoomDao, 1)
        TestConstants.SIZES.forEach { size ->
            for (repeat in 0 until 5) {
                Log.d("###hash", repeat.toString())
               citiesLoaderRoom.readdb(context, resultSet, cityRoomDao, size)

            }
        }
        citiesLoaderRoom.deletedb(context, resultSet, cityRoomDao, 1)

        Saver().save(resultSet,"RoomResults.csv",CitiesLoaderRoom.TAG )
    }

    @org.junit.Test
    fun testDatabasesRealm() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        val application = context as MyApplication
        val citiesLoaderRealm = CitiesLoaderRealm()


        val realm = Realm.getDefaultInstance()


        citiesLoaderRealm.createdb(context, resultSet, realm, 1)
        TestConstants.SIZES.forEach { size ->
            for (repeat in 0 until 5) {
                Log.d("###hash", repeat.toString())

                citiesLoaderRealm.readdb(context, resultSet, realm, size)

            }
        }
        citiesLoaderRealm.deletedb(context, resultSet, realm, 1)

        Saver().save(resultSet,"RealmResults.csv", CitiesLoaderRealm.TAG)
    }
}