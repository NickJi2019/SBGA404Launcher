package com.woznes.sbga404launcher

import com.woznes.sbga404launcher.Utils.fuckYou
import java.io.File
import java.io.InputStream

fun String.isDir(): Boolean {
    return File(this).isDirectory
}

fun String.isFile(): Boolean {
    return File(this).isFile
}

fun String.newDir() {
    if (!File(this).mkdirs()) {
        fuckYou<Unit>("fail to create directory")
    }
}

fun String.newFile() {
    if (!File(this).createNewFile()) {
        return fuckYou("fail to create file")
    }
}

fun Any.printIt(): Any {
    print(this)
    return this;
}

fun Any.printlnIt(): Any {
    println(this)
    return this
}
fun Process.printResult(): Unit {
    val result = String(this.inputStream.readAllBytes())
    val err = String(this.errorStream.readAllBytes())
    for (i in Utils.spriteString(result)) {
        Sbga404LauncherApplication.log.info(i)
    }
    if (err != "") {
        for (i in Utils.spriteString(err)) {
            Sbga404LauncherApplication.log.error(i)
        }
    }
}
fun InputStream.printResult(): Unit {
    val result = String(this.readAllBytes())
    for (i in Utils.spriteString(result)) {
        Sbga404LauncherApplication.log.info(i)
    }

}