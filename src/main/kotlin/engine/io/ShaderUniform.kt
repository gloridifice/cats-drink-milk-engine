package engine.io

import org.joml.Matrix4f
import org.joml.Vector2f
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL33

class ShaderUniform<out T>(val name: String, val value : T) {
    fun use(shader: Int){
        val location = GL33.glGetUniformLocation(shader, name)
        when{
            value is Float -> GL33.glUniform1f(location, value)
            value is Boolean -> GL33.glUniform1i(location, if (value) 1 else 0)
            value is Int -> GL33.glUniform1i(location, value)
            value is Vector4f -> GL33.glUniform4f(location, value.x, value.y, value.z, value.w)
            value is Vector3f -> GL33.glUniform3f(location, value.x, value.y, value.z)
            value is Vector2f -> GL33.glUniform2f(location, value.x, value.y)
            value is Matrix4f -> GL33.glUniformMatrix4fv(location, false, value.get(BufferUtils.createFloatBuffer(16)))

            else -> {
                val type = if (value == null) "null" else value!!::class.simpleName
                throw IllegalArgumentException("Shader Uniform type not permitted: $type !")
            }
        }
    }
}