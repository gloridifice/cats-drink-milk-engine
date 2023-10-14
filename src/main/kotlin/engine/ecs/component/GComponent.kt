package engine.ecs.component

import engine.ecs.GObject
import engine.ecs.node.GNode
import engine.warn

open class GComponent: GObject() {
    lateinit var parent: GNode
    open fun logic(){

    }
    open fun render(){

    }

}