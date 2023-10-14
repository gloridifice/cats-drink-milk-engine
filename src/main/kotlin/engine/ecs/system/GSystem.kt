package engine.ecs.system

import engine.ecs.GObject
import engine.event.EngineEventCtx

open class GSystem(init: EngineEventCtx.Init): GObject() {
    open fun logic(){

    }
    open fun render(){

    }
}