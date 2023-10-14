package engine.event

import uno.gl.GlWindow

open class GEventCtx {
}

open class EngineEventCtx: GEventCtx(){
    class Init(gAppWindow: GlWindow): EngineEventCtx()
    class Update: EngineEventCtx()

}
