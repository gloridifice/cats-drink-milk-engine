package engine.ecs.system

import engine.Engine
import engine.event.EngineEventCtx
import engine.io.ShaderUniform
import engine.render.MeshRenderer

class RenderSystem(init: EngineEventCtx.Init) : GSystem(init) {
    companion object {
        val INSTANCE by lazy { Engine.INSTANCE.findSystem(RenderSystem::class.java) }
    }

    private val meshRenderers = HashSet<MeshRenderer>()

    fun addMeshRenderer(renderer: MeshRenderer){
        meshRenderers.add(renderer)
    }
    fun removeMeshRender(renderer: MeshRenderer){
        meshRenderers.remove(renderer)
    }
    fun camera() = Engine.INSTANCE.currentRoot.camera
    init {

    }

    override fun render() {
        val camera = camera()
        val uniforms = arrayOf(
            ShaderUniform("uViewMatrix", camera.viewMatrix()),
            ShaderUniform("uProjMatrix", camera.projectionMatrix())
        )
        meshRenderers.forEach {
            it.render(uniforms)
        }
    }
}