package engine.render

import engine.Engine
import org.joml.Matrix4f
import org.joml.Quaternionf
import org.joml.Vector3f
import toRadians

class Camera(
    val farPlain: Float = 100f,
    val nearPlain: Float = 0.1f,
    val fovy: Float = 270f.toRadians(),
    val position: Vector3f = Vector3f(),
    val rotation: Quaternionf = Quaternionf(),
) {

    fun viewMatrix(): Matrix4f{
        return Matrix4f().translate(position).rotate(rotation).invert()
    }

    fun projectionMatrix(): Matrix4f{
        val aspect = Engine.INSTANCE.gAppWindow.aspect
        val mat4 = Matrix4f().perspective(fovy, aspect, nearPlain, farPlain)
        return mat4
    }
}