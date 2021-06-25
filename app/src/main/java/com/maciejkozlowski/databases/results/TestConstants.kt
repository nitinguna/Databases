package com.maciejkozlowski.databases.results

import com.maciejkozlowski.databases.greendao.CitiesLoaderDao
import com.maciejkozlowski.databases.objectbox.CitiesLoaderBox
import com.maciejkozlowski.databases.realm.CitiesLoaderRealm
import com.maciejkozlowski.databases.room.CitiesLoaderRoom
import com.maciejkozlowski.databases.sqlite.CitiesLoaderSql

/**
 * Created by Maciej on 2017-05-17.
 */

object TestConstants {

    var SIZES = listOf(100)
    //var TYPES = listOf(CitiesLoaderSql.TAG, CitiesLoaderRoom.TAG, CitiesLoaderDao.TAG,
     //       CitiesLoaderRealm.TAG, CitiesLoaderBox.TAG)

    var TYPES = listOf(CitiesLoaderRealm.TAG)

}
