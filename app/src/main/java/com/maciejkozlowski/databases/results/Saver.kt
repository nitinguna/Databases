package com.maciejkozlowski.databases.results

import android.os.Environment
import android.util.Log

import java.io.File
import java.io.FileOutputStream
import java.io.IOException


/**
 * Created by Maciej on 2017-05-10.
 */

class Saver {

    private var outputStream: FileOutputStream? = null
    private var opened: Boolean = false

    fun open(resultFile : String) {
        if (!opened) {
            try {
                val file = File(Environment.getExternalStorageDirectory(), resultFile)
                if (file.exists()) {
                    file.delete()
                }

                outputStream = FileOutputStream(file)
                opened = true
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    fun write(data: String) {
        if (opened) {
            try {
                outputStream!!.write(data.toByteArray())
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        } else {
            Log.e(TAG, "NOT OPENED")
        }
    }

    fun close() {
        if (opened) {
            try {
                outputStream!!.close()
                opened = false
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    fun save(resultSet: ResultSet, resultFile : String , type : String) {
        open(resultFile)

//        saveResult(resultSet.creating)
        saveResult(resultSet.reading, resultFile,type)
//        saveResult(resultSet.deleting)
//        saveResult(resultSet.updating)

        close()
    }

    private fun saveResult(result: Result, resultFile: String, type : String) {
        var key: String? = null
        var timesAvg: String? = null
        write(result.type + NEW_LINE)
        //for (type in TestConstants.TYPES) {
            for (size in TestConstants.SIZES) {
                key = size.toString() + "-" + type
                val times = result.times[key]
                times.let {it ->
                    if (it != null) {
                        var i:Int =0
                        for (it1 in it) {
                             i=i+1;
                            val keyit = type+"-"+i
                            write(keyit + SEPARATOR)
                            write(it1.toString() + SEPARATOR)
                            write(NEW_LINE)
                        }
                    }

                }
                 timesAvg = times!!.average().toString()
            }
        write(key+"avg" + SEPARATOR)
        write(timesAvg + SEPARATOR)
            write(NEW_LINE)
        //}
        write(NEW_LINE)
        write(NEW_LINE)
    }

    companion object {
        const val FILE_NAME = "results.csv"
        const val TAG = "###"
        const val SEPARATOR = ";"
        const val NEW_LINE = "\n"
    }
}
