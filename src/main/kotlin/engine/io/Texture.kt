package engine.io

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL33
import org.lwjgl.stb.STBImage

class Texture(private val path : String){
    val id : Int
    init {
        val image = Image(path)

        val texture = GL33.glGenTextures()
        GL33.glBindTexture(GL33.GL_TEXTURE_2D, texture)

        GL33.glTexParameteri(GL33.GL_TEXTURE_2D, GL33.GL_TEXTURE_WRAP_S, GL33.GL_REPEAT)
        GL33.glTexParameteri(GL33.GL_TEXTURE_2D, GL33.GL_TEXTURE_WRAP_T, GL33.GL_REPEAT)
        GL33.glTexParameteri(GL33.GL_TEXTURE_2D, GL33.GL_TEXTURE_MIN_FILTER, GL33.GL_LINEAR_MIPMAP_LINEAR)
        GL33.glTexParameteri(GL33.GL_TEXTURE_2D, GL33.GL_TEXTURE_MAG_FILTER, GL33.GL_LINEAR)

        GL11.glTexImage2D(
            GL33.GL_TEXTURE_2D, 0,
            GL33.GL_RGB, image.width, image.width, 0,
            GL33.GL_RGB,
            GL33.GL_UNSIGNED_BYTE, image.stbImage)
        GL33.glGenerateMipmap(GL33.GL_TEXTURE_2D)

        GL33.glBindTexture(GL33.GL_TEXTURE_2D, 0)
        image.free()

        id = texture
    }
    fun use(){
        GL33.glBindTexture(GL33.GL_TEXTURE_2D, id)
    }
}