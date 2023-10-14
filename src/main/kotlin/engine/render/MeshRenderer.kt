package engine.render

import engine.ecs.component.GComponent
import engine.ecs.system.RenderSystem
import engine.io.ShaderUniform
import org.joml.Matrix4f
import org.joml.Vector3f
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL33

class MeshRenderer(
    val material: Material,
    val meshInstance: MeshInstance
) : GComponent(){
    init {
        RenderSystem.INSTANCE?.addMeshRenderer(this)
    }
    fun render(uniforms:Array<out ShaderUniform<*>>){
        material.render(meshInstance, uniforms)
    }

    override fun onDestroy() {
        RenderSystem.INSTANCE?.removeMeshRender(this)
        super.onDestroy()
    }
}