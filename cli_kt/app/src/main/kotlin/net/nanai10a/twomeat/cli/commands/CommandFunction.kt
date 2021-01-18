package net.nanai10a.twomeat.cli.commands

interface CommandFunction {
    val info: CommandFunctionInfo
    fun isCallable(args: List<String>): Boolean {
        val isPrefixesMatch =
            args.slice(this.info.prefixes.indices)
                .containsAll(this.info.prefixes)

        val isCommandLengthMatch =
            if (this.info.isVariableLength) true
            else args.size == (this.info.argsLength!!)

        return isPrefixesMatch && isCommandLengthMatch
    }

    fun call(args: List<String>)
}
