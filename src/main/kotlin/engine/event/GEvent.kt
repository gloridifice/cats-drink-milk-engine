package engine.event

import uno.gl.GlWindow

open class GEvent {
}

open class EngineEvent: GEvent(){
    class EngineInit(gAppWindow: GlWindow): EngineEvent()
    class Update: EngineEvent()

}
