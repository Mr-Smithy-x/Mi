package io.vonley.mi.di.network.listeners

interface OnConsoleListener : OnClientListener {

    fun onEmptyDataReceived()

    fun onAlreadyStored()

}