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

        assertEquals(
            listOf("When", "the", "ferengi", "resists", "for", "nowhere,", "all", "ships", "gather", "post-apocalyptic,", "clear", "nanomachines."),
            func("When    the    ferengi resists for      nowhere,    all ships gather   post-apocalyptic, clear nanomachines.")
        )

        assertEquals(
            listOf("Happiness", "happens", "when", "you", "need", "extend", "so", "agreeable", "that", "whatsoever you are", "contacting", "is", "your", "vision."),
            func("""Happiness happens when you need extend so agreeable that "whatsoever you are" contacting is your vision.""")
        )

        assertEquals(
            listOf("a", "b", "c", "d"),
            func("a　b\tc d")
        )

        assertEquals(
            listOf("a", "b"),
            func("a　\t\n\rb")
        )

        assertEquals(
            listOf("a b"),
            func("a\\ b")
        )

        assertEquals(
            listOf(""),
            func(" ")
        )

        assertEquals(
            listOf("hey"),
            func("""
                 |hey
             """.trimMargin("|"))
        )

        assertEquals(
            listOf("hoge??", "　", "fuga??"),
            func("""
                 |hoge??
                 |\　
                 |　　　
                 |fuga??
             """.trimMargin("|"))
        )
    }
}
