package engine.render

import engine.io.ShaderUniform
import org.joml.Matrix4f
import org.joml.Vector3f
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL33

class MeshRenderer(
    val material: Material,
    val meshInstance: MeshInstance
) {
    fun render(uniforms:Array<out ShaderUniform<*>>){
        material.render(meshInstance, uniforms)
    }
}