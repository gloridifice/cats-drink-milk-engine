package engine.render

import engine.io.Resource
import engine.io.Shader
import engine.io.ShaderUniform
import engine.io.Texture
import org.joml.Matrix4f
import org.joml.Vector3f
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL33

class Material(
    val vertShaderPath: String,
    val fragShaderPath: String,
    val texturePath: String,
) {
    val shader: Shader = Shader(vertShaderPath, fragShaderPath)
    val texture: Texture = Texture(texturePath)

    fun render(meshInstance: MeshInstance, uniforms: Array<out ShaderUniform<*>>){
        meshInstance.bindShader(shader)
//        val mat4 = Matrix4f()
//        mat4.rotate(0f, Vector3f(0f,0f,1f))

        shader.use(*uniforms)
        texture.use()

        meshInstance.use()

        GL11.glDrawElements(GL33.GL_TRIANGLES, meshInstance.mesh.indices.size, GL33.GL_UNSIGNED_INT, 0)
        GL33.glBindVertexArray(0)
    }
}