package engine

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL33
import java.lang.IllegalStateException


class Window(
    val height: Int, val width: Int, val title: String
) {
    val glWindow: Long
    val aspect : Float
        get() = width.toFloat() / height.toFloat()

    init {
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3)
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL33.GL_TRUE)

        glWindow = glfwCreateWindow(width, height, title, 0, 0)
        if (glWindow == 0L) throw IllegalStateException("Failed to create window!")

        glfwMakeContextCurrent(glWindow)
        glfwShowWindow(glWindow)
    }
}