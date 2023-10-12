package engine.io

import org.lwjgl.stb.STBImage
import org.lwjgl.system.MemoryStack
import utils.IOUtils
import java.lang.RuntimeException
import java.nio.ByteBuffer

class Image(private val imagePath: String) {
    val stbImage : ByteBuffer
    val width : Int
    val height : Int
    init {
        val stack = MemoryStack.stackPush()
        val width = stack.mallocInt(1)
        val height = stack.mallocInt(1)
        val nrChannel = stack.mallocInt(1)
        val memoryBuf = IOUtils.ioResourceToByteBuffer(Resource(ResourceType.TEXTURE, imagePath).fileName, 8 * 1024)
        val buf = STBImage.stbi_load_from_memory(memoryBuf, width, height, nrChannel, 0)
            ?: throw RuntimeException("Fail to load Image - path: ${imagePath}")
        stbImage = buf
        this.width = width.get()
        this.height = height.get()
    }
    fun free(){
       STBImage.stbi_image_free(stbImage)
    }
}