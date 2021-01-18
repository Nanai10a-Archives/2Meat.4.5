package net.nanai10a.twomeat.cli.utils

class DIService {
    private val constructors = mutableMapOf<Class<*>, Function<*>>()

    fun <T> register(interfaceClass: Class<T>, constructor: Function<T>) {
        if (constructors.keys.contains(interfaceClass))
            throw Exception("The specified interface was already registered!")

        this.constructors[interfaceClass] = constructor
    }

    fun create(interfaceClass: Class<*>): Any {
        if (!constructors.keys.contains(interfaceClass))
            throw Exception("The specified interface was not registered!")

        return this.constructors[interfaceClass] as Any
    }
}
