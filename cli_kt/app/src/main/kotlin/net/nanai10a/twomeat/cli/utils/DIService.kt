package net.nanai10a.twomeat.cli.utils

class DIService(internal val env: Env = Env()) {
    private val constructors = mutableMapOf<Class<*>, (() -> Any)>()

    fun <T> register(interfaceClass: Class<T>, constructor: () -> T) {
        if (constructors.keys.contains(interfaceClass))
            throw Exception("The specified interface was already registered!")

        @Suppress("UNCHECKED_CAST")
        this.constructors[interfaceClass] = constructor as () -> Any
    }

    fun create(interfaceClass: Class<*>): Any {
        if (!constructors.keys.contains(interfaceClass))
            throw Exception("The specified interface was not registered!")

        return this.constructors[interfaceClass] as Any
    }
}
