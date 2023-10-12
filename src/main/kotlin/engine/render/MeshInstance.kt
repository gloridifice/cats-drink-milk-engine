package engine.render

import engine.io.Shader
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL33

class MeshInstance(val mesh: Mesh) {
    val vao: Int = GL33.glGenVertexArrays()
    val vbo: Int = GL33.glGenBuffers()
    val ebo: Int = GL33.glGenBuffers()

    init {
        GL33.glBindVertexArray(vao)

        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, vbo)
        GL15.glBufferData(GL33.GL_ARRAY_BUFFER, mesh.vertices, GL33.GL_STATIC_DRAW)

        GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, ebo)
        GL15.glBufferData(GL33.GL_ELEMENT_ARRAY_BUFFER, mesh.indices, GL33.GL_STATIC_DRAW)

        var pointer = 0L
        val stride = mesh.attributes.sumOf { it.size }
        for (i in mesh.attributes.indices){
            val attri = mesh.attributes[i]
            GL20.glVertexAttribPointer(i, attri.size, GL33.GL_FLOAT, false, stride * Float.SIZE_BYTES, pointer * 4)
            GL33.glEnableVertexAttribArray(i)
            pointer += attri.size
        }

        GL33.glBindVertexArray(0)
    }

    fun bindShader(shader: Shader){
//        for (i in mesh.attributes.indices){
//            val attri = mesh.attributes[i]
//            GL20.glBindAttribLocation(shader.programId, i, attri.name)
//        }
    }

    fun use(){
        GL33.glBindVertexArray(vao)
    }
}