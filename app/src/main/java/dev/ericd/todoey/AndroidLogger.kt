package dev.ericd.todoey

import android.util.Log
import dev.ericd.todoey.common.logs.Logger

class AndroidLogger : Logger {

    override fun logMessage(message: String) {
        Log.d("LOGGER", message)
    }

}