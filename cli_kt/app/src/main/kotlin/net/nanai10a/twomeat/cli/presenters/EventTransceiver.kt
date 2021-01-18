package net.nanai10a.twomeat.cli.presenters

interface EventReceiver<E> {
    fun onReceive(event: E)
}

interface EventTransmissioner<E> {
    val receivers: List<EventReceiver<E>>
    fun transmission(event: E) {
        receivers.forEach { it.onReceive(event) }
    }
}
