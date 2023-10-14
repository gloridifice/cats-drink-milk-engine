package engine.event

object GEventBus {
    val onLogic = GEventObserver<EngineEvent.Update>()

    inline fun <reified T: GEvent> addListenerByType(noinline block: (event: T) -> Unit){
        this::class.java.fields.forEach {
            if (it.type == GEventObserver::class.java && it.genericType == T::class.java){
                val ob = (it.get(null)) as GEventObserver<T>
                ob.addListener(0, block)
            }
        }
    }
}