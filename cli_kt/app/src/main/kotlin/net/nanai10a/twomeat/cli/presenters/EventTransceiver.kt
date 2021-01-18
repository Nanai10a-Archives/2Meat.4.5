package net.nanai10a.twomeat.cli.presenters

interface EventReceiver<E> {
    fun onReceive(event: E)
}

interface EventTransmissioner<E> {
    val receivers: MutableList<EventReceiver<E>> get() = throw RuntimeException("don't access this.")
    fun addReceivers(vararg receivers: EventReceiver<E>) {
        this.receivers.addAll(receivers)
    }

    fun removeReceivers(vararg receivers: EventReceiver<E>) {
        this.receivers.removeAll(receivers)
    }

    fun transmission(event: E) {
        receivers.forEach { it.onReceive(event) }
    }
}
