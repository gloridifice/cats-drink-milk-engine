package engine.io

import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL33.*
import java.io.File

class Shader(private val vertPath : Resource, private val fragPath : Resource) {
    constructor(vert: String, frag: String): this(
        Resource(ResourceType.SHADER, "${vert}.vert"),
        Resource(ResourceType.SHADER, "${frag}.frag")
    )
    val programId = glCreateProgram()
    init {
        val vertString = File(vertPath.getURL().toURI()).reader().use { it.readText() }
        val fragString = File(fragPath.getURL().toURI()).reader().use { it.readText() }

        val vertShader = glCreateShader(GL_VERTEX_SHADER)
        val fragShader = glCreateShader(GL_FRAGMENT_SHADER)

        GL20.glShaderSource(vertShader, vertString)
        glCompileShader(vertShader)
        checkShaderError(vertShader)

        GL20.glShaderSource(fragShader, fragString)
        glCompileShader(fragShader)
        checkShaderError(fragShader)

        glAttachShader(programId, vertShader)
        glAttachShader(programId, fragShader)

        glLinkProgram(programId)
        checkProgramError(programId)

        glDeleteShader(vertShader)
        glDeleteShader(fragShader)
    }
    fun use(vararg array: ShaderUniform<*>){
        glUseProgram(programId)
        array.forEach {
            it.use(programId)
        }
    }

    private fun checkShaderError(shader : Int){
        val success = GL20.glGetShaderi(shader, GL_COMPILE_STATUS)
        if (success == GL_FALSE){
            val log = GL20.glGetShaderInfoLog(shader)
            error("ERROR::SHADER::COMPILATION_FAILED\n$log")
        }
    }

    private fun checkProgramError(program: Int){
        val success = glGetProgrami(program, GL_LINK_STATUS)
        if (success == GL_FALSE){
            val log = GL20.glGetProgramInfoLog(program)
            error("ERROR::PROGRAM::LINK_FAILED\n$log")
        }
    }
}