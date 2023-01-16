package com.woznes.sbga404launcher

import java.util.prefs.Preferences
import com.woznes.sbga404launcher.Sbga404LauncherApplication.Companion.log
import org.apache.commons.io.FileUtils
import java.io.File
import java.nio.charset.Charset
import kotlin.system.exitProcess

object Preferences {
    private val preferences: Preferences = Preferences.userRoot().node(javaClass.name)
    val keys = arrayListOf("config.js","segatools.ini","shadow_start.bat","start.bat")
    fun reset(): Unit {
        for (i in keys){
            preferences.put(i,"")
        }
        log.warn("Preferences has been reset")
        log.warn("please restart the program")
        exitProcess(0)
    }
    fun getAllPre():HashMap<String,String>{
        val map= hashMapOf<String,String>()
        for (i in keys){
            map[i] = preferences.get(i,"")
        }
        return map
    }
    fun getPre(key:String):String{
        return preferences.get(key,"")
    }
    fun getPre(key: Int): String {
        return preferences.get(keys[key], "")
    }
    fun setPre(key: String,value:String): Unit {
        preferences.put(key,value)
    }
    fun setPre(key: Int, value: String): Unit {
        preferences.put(keys[key], value)
    }
    fun isNew():Boolean{
        val all = getAllPre()
        for (i in keys){
            if (all[i]!=""){
                return false
            }
        }
        return true
    }
    fun writeConfigFile(path: Int, identifier: String, data: String) {
        StringBuffer(Utils.readResources(keys[path])).also {
            val index = it.indexOf(identifier) + identifier.length
            it.delete(index, index + data.length)
            it.insert(index, data)
            val f = File("${getPre(path)}/${keys[path]}")
            Utils.resetFile(f)
            FileUtils.writeStringToFile(f, it.toString(), Charset.defaultCharset(), false)
        }
    }
    fun writePre(a: Int, message: String) {
        do {
            Utils.log.warn(message)
            setPre(a, Sbga404LauncherApplication.sc.next())
            if (!getPre(a).isFile() and (a != 0) and (a != 1)) {
                Utils.log.error("It doesn't look like a file")
                Utils.log.warn("[R]:reset it (Default)\t [S]:skip")
                when (val choice = Sbga404LauncherApplication.sc.next()) {
                    "r", "R" -> continue
                    "s", "S" -> break
                    else -> {
                        choice.newFile()
                        break
                    }
                }
            } else if (!getPre(a).isDir() and (a != 2) and (a != 3)) {
                Utils.log.error("It doesn't look like a directory")
                Utils.log.warn("[R]:reset it\t [S]:skip\t [C]try to create directory (Default)")
                when (val choice = Sbga404LauncherApplication.sc.next()) {
                    "r", "R" -> continue
                    "s", "S" -> break
                    "c", "C" -> choice.newDir()
                    else -> {
                        choice.newDir()
                        break
                    }
                }
            } else if (getPre(a).isFile() or (getPre(a).isDir() and ((a == 0) or (a == 1)))) {
                break
            } else {
                Utils.fuckYou<Unit>("I don't know what happened")
            }
        } while (true)
        Utils.log.warn("OK")
    }
}