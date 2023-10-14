package engine.event

import java.util.TreeMap


class GEventObserver<T : GEventCtx> {
    private val map = TreeMap<Int, MutableSet<(event: T) -> Unit>>()
    fun addListener(order: Int = 0, block: (event: T) -> Unit) {
        map[order]?.add(block) ?: { map[order] = mutableSetOf(block) }
    }
    fun removeListener(block: (event: T) -> Unit){
        map.forEach { (_, set) -> set.remove(block) }
    }

    fun invoke(event: T) {
        map.forEach { (_, set) -> set.forEach { it.invoke(event) } }
    }
}
