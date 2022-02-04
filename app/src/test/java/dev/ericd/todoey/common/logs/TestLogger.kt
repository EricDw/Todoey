package dev.ericd.todoey.common.logs

class TestLogger : Logger {

    override fun logMessage(message: String) {
        println(message)
    }

}
