package engine.ecs.node

import engine.ecs.GObject
import engine.ecs.component.GComponent

open class GNode: GObject(
) {
    private val components = ArrayList<GComponent>()

    val children = ArrayList<GComponent>()
    init {

    }

    fun <T: GComponent> addComponent(component: T): T{
        components.add(component)
        component.parent = this
        return component
    }
    fun removeComponent(component: GComponent){
        components.remove(component)
    }

    open fun logic(){
        components.forEach { it.logic() }
    }
    open fun render(){
        components.forEach { it.render() }
    }
}