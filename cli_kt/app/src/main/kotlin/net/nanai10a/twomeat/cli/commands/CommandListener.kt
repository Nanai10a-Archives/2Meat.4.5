package net.nanai10a.twomeat.cli.commands

class CommandListener {
    private val functions = mutableListOf<CommandFunction>()
    fun addCommandFunction(vararg functions: CommandFunction) {
        this.functions.addAll(functions)
    }

    fun removeCommandFunction(vararg functions: CommandFunction) {
        this.functions.removeAll(functions)
    }

    private fun onCommandWithParsedArgs(args: List<String>) {
        val matchList = mutableListOf<CommandFunction>()
        this.functions.forEach { function ->
            if (function.isCallable(args))
                matchList.add(function)
        }

        if (matchList.size >= 2)
            throw RuntimeException("commands was matched 2 or more command_function! (expect 1 or none)")

        matchList.firstOrNull()?.call(args)
    }

    fun onCommand(nonParsedArgs: String) {
        this.onCommandWithParsedArgs(parseCommand(nonParsedArgs))
    }
}

/**
 * **rules:**
 *
 * 1. \の次の文字は必ずそのまま受け取る.
 * 2. ""で囲われた文字列は結合した状態で一つの文字列として扱われる. (又, その""は無いものとして扱う.)
 * 3. スペースは区切りとして受け取る.
 * 4. その他の文字は文字列の一部として受け取る.
 *
 * ※スペース: 半角, 全角, TAB, 改行(\r & \n)
 */
fun parseCommand(rawCommand: String): List<String> {
    val commands = mutableListOf<String>()
    val charBuffer = mutableListOf<Char>()
    var isReamingDoubleQuotation = false
    var isNextCharEscape = false

    rawCommand.forEach { char ->
        // 1.
        if (isNextCharEscape) {
            charBuffer.add(char)
            return@forEach
        }

        val charStr = char.toString()

        // 1.
        if (charStr == "\\") {
            isNextCharEscape = true
            return@forEach
        }

        // 2.
        if (charStr == "\"") {
            if (isReamingDoubleQuotation) {
                isReamingDoubleQuotation = false
                return@forEach
            } else {
                isReamingDoubleQuotation = true
                return@forEach
            }
        }

        // 2.
        if (isReamingDoubleQuotation) {
            charBuffer.add(char)
            return@forEach
        }

        // 3.
        if (
            charStr == "\u0020"
            || charStr == "\u3000"
            || charStr == "\u0009"
            || charStr == "\u000d"
            || charStr == "\u000a"
        ) {
            if (charBuffer.isEmpty())
                return@forEach

            var command = ""
            charBuffer.forEach { _char ->
                command += command + _char
            }

            charBuffer.clear()

            commands.add(command)
            return@forEach
        }

        // 4.
        charBuffer.add(char)
    }

    return commands
}
