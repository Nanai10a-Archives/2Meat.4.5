package net.nanai10a.twomeat.cli.commands

import org.junit.jupiter.api.Assertions.assertEquals
import net.nanai10a.twomeat.cli.commands.parseCommand as func

internal class ParseCommandTest {
    @org.junit.jupiter.api.Test
    fun parseCommand() {
        assertEquals(
            listOf("A", "falsis,", "devatio", "magnum", "eleates."),
            func("A falsis, devatio magnum eleates.")
        )
    }
}
