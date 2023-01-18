package com.woznes.sbga404launcher


import com.woznes.sbga404launcher.Sbga404LauncherApplication.Companion.log
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class Sbga404LauncherApplicationTests {

    @Test
    fun context(){
        Preferences.isNew().also { log.warn(it) }
        Preferences.getAllPre().also {
            for (i in Preferences.keys){
                log.warn(it[i])
            }
        }
        Preferences.setPre(0,"/")
    }

    @Test
    fun context2(){
        Preferences.reset()
    }
    @Test
    fun context3(){
        val instance = Utils.loadLibrary("test")
        println(instance.jnaTest())
    }

}
