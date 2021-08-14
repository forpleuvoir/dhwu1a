package forpleuvoir.dhwu1a.console

import forpleuvoir.dhwu1a.core.Dhwu1a

/**
 * 控制台程序
 * @author forpleuvoir

 * #project_name dhwu1a

 * #package forpleuvoir.dhwu1a.console

 * #class_name Dhwu1aConsole

 * #create_time 2021/8/12 22:36
 */
fun main(args: Array<String>) {
    val dhwu1aConsole = Dhwu1aConsole.instance
    dhwu1aConsole.initialize(args)
    dhwu1aConsole.start()
}

class Dhwu1aConsole {
    companion object {
        val instance: Dhwu1aConsole by lazy { Dhwu1aConsole() }
    }

    private val dhwu1a: Dhwu1a = Dhwu1a.instance

    fun initialize(args: Array<String>) {

    }

    fun start() {
        dhwu1a.start()
    }
}