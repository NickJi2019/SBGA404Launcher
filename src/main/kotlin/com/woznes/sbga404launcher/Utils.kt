package com.woznes.sbga404launcher

import org.apache.commons.logging.LogFactory
import org.testng.annotations.Test
import java.io.*
import java.lang.StringBuilder
import kotlin.system.exitProcess


object Utils {
    val log = LogFactory.getLog(this.javaClass)
    fun fuckYou(): Any {
        log.fatal("Fuck you")
        throw Throwable("Fuck you")
    }

    fun <T> fuckYou(message: String): T {
        log.fatal("$message but fuck you")
        throw Throwable(message)
    }
    fun resetFile(f: File) {
        if (!f.isFile) {
            f.delete()
            f.createNewFile()
        }
    }

    fun readResources(name: String): String {
        Sbga404LauncherApplication.Companion::class.java.classLoader.getResource(name).also {
            if (it == null) {
                fuckYou()
            }
            return String(it.openConnection().getInputStream().readAllBytes());
        }
    }

     fun selectPort(): Int {
        return when (Sbga404LauncherApplication.sc.next()) {
            "3" -> {
                37703
            }

            "4" -> {
                37704
            }

            "5" -> {
                37705
            }

            "6" -> {
                37706
            }

            else -> fuckYou() as Int
        }
    }

     fun selectIP(): Int {
        return when (Sbga404LauncherApplication.sc.next()) {
            "1" -> 11
            "2" -> 12
            "3" -> 13
            "4" -> 14
            else -> fuckYou() as Int
        }
    }


    fun checkFile(a: Int): Boolean {
        return !(!Preferences.getPre(a).isFile() and (a != 0) and (a != 1)
                or !Preferences.getPre(a).isDir() and (a != 2) and (a != 3))
    }


    fun spriteString(s:String):ArrayList<String>{
        val result= arrayListOf<String>()
        var tmpStr=""
        for (i in s){
            if (i!='\n'){
                tmpStr+=i
            }else{
                result.add(tmpStr)
                tmpStr=""
            }
        }
        return result
    }


    fun checkWindows():Unit{
        System.getProperty("os.name").indexOf("Windows").also {
            if (it==-1){
                return fuckYou("this is not windows")
            }
        }
    }
    fun checkBadKeys() {
        if (!Preferences.isNew()) {
            for (i in 0..3) {
                checkFile(i).also {
                    if (!it) {
                        Sbga404LauncherApplication.log.error("the path of ${Preferences.keys[i]} seems like bad")
                    }
                }
            }
        }
    }


    fun gbk2utf(inputStream: InputStream): String {
        return String(inputStream.readBytes(), charset("GBK"))
    }
}