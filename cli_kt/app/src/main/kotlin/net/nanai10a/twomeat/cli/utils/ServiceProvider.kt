package net.nanai10a.twomeat.cli.utils

class ServiceProvider(internal val env: Env = Env()) {
    private val constructors = mutableMapOf<Class<*>, (() -> Any)>()

    fun <T> register(targetClass: Class<T>, constructor: () -> T) {
        if (constructors.keys.contains(targetClass))
            throw Exception("The specified interface was already registered!")

        @Suppress("UNCHECKED_CAST")
        this.constructors[targetClass] = constructor as () -> Any
    }

    fun <T> create(targetClass: Class<T>): T {
        if (!constructors.keys.contains(targetClass))
            throw Exception("The specified interface was not registered!")

        @Suppress("UNCHECKED_CAST")
        return this.constructors[targetClass] as T
    }
}
