package com.woznes.sbga404launcher

import com.sun.jna.Library

interface Jna : Library {
    fun jnaTest():String
}