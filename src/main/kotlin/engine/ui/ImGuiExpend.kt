package engine.ui

import com.github.ajalt.mordant.table.horizontalLayout
import glm_.vec3.Vec3
import imgui.ImGui

object ImGuiExpend {
    fun vector3Input(label: String, vec3: Vec3){
        with(ImGui){
            val xBuf = floatArrayOf(vec3.x)
            val yBuf = floatArrayOf(vec3.y)
            val zBuf = floatArrayOf(vec3.z)
            horizontalLayout {
                input("x", xBuf)
                input("x", yBuf)
                input("z", zBuf)
                text(label)
            }
            vec3.x = xBuf.first()
            vec3.y = yBuf.first()
            vec3.z = zBuf.first()
        }
    }
}