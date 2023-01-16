package com.woznes.sbga404launcher


import com.woznes.sbga404launcher.Utils.checkBadKeys
import com.woznes.sbga404launcher.Utils.checkWindows
import com.woznes.sbga404launcher.Utils.gbk2utf
import com.woznes.sbga404launcher.Utils.selectIP
import com.woznes.sbga404launcher.Utils.selectPort
import org.apache.commons.logging.LogFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.nio.charset.Charset
import java.util.*


@SpringBootApplication
class Sbga404LauncherApplication{

    companion object {
        val sc = Scanner(System.`in`)
        val log = LogFactory.getLog(Companion::class.java)

        private fun startup(){
            log.warn("This is the first startup")
            log.warn("We're going to set something up")
            Preferences.writePre(0,"where will output the 'config.js': ")
            Preferences.writePre(1,"where will output the 'segatools.ini': ")
            Preferences.writePre(2,"where is 'shadow_start.bat': ")
            Preferences.writePre(3,"where is 'start.bat': ")
        }



        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Sbga404LauncherApplication::class.java)
            checkWindows()
            checkBadKeys()
            log.warn(System.getProperty("file.encoding"))
            log.warn("\t\tWelcome!")
            log.warn("[S]start, [R]reset preferences")

            when(sc.next()){
                "S","s"->{}
                "R","r"->Preferences.reset()
                else->{}
            }

            if (Preferences.isNew()) {
                startup()
            }


            log.warn("Select a port:[3]37703 [4]37704 [5]37705 [6]37706: ")
            Preferences.writeConfigFile(0,"server_port\":", selectPort().toString())

            log.warn("Select a IP Address: 192.168.139.[] : [1]11, [2]12, [3]13, [4]14: ")
            val ip = selectIP()
            Preferences.writeConfigFile(1,"addrSuffix=",ip.toString())

            val rt=Runtime.getRuntime()

            rt.exec("\"C:\\Windows\\System32\\netsh.exe\" interface ipv4 add address \"Loopback Pseudo-Interface 1\" 192.168.139.$ip 255.255.255.0 store=active").also {
                val mes= gbk2utf(it.inputStream).trim()
                log.info(mes)
            }
            rt.exec(Preferences.getPre(2)).printResult()
            rt.exec(Preferences.getPre(3)).also {
                it.printResult()
                it.waitFor()
            }
        }


    }
}
