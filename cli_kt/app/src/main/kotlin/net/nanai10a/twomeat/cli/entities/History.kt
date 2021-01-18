package net.nanai10a.twomeat.cli.entities

data class History(val history: Array<Command>) {
    // Generated.
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as History

        if (!history.contentEquals(other.history)) return false

        return true
    }

    override fun hashCode(): Int {
        return history.contentHashCode()
    }
}
