package engine.io

import engine.info
import engine.render.Mesh
import engine.render.VertexAttribute
import engine.warn
import org.lwjgl.assimp.AIMesh
import org.lwjgl.assimp.Assimp

object ModelLoader {
    fun loadModelFile(filePath: String): List<Mesh>? {
        val scene = Assimp.aiImportFile(Resource(ResourceType.MODEL, filePath).getURL().path, Assimp.aiProcess_Triangulate)

        scene?.let { aiScene ->
            val meshes = aiScene.mMeshes()
            meshes?.let { meshes ->
                val list = ArrayList<Mesh>()
                for (i in 0 until meshes.limit()) {
                    val mesh = AIMesh.create(meshes.get(i))
                    list.add(processMesh(mesh))
                }

                info {
                    "Loading Model Success: $filePath"
                }
                return list
            } ?: warn { "Load Model File Failed: meshes is null. File Path: ${filePath}" }
        } ?: warn { "Load Model File Failed: aiScene is null. File Path: ${filePath}" }

        return null
    }
    private fun processMesh(mesh: AIMesh): Mesh{
        val indices = ArrayList<Int>()
        val vertices = ArrayList<Float>()

        val attributes = arrayOf(
            VertexAttribute("position", 3),
            VertexAttribute("texCoord0", 2),
            VertexAttribute("normal", 3),
            VertexAttribute("color", 4),
        )

        for (j in 0 until mesh.mVertices().limit()){
            val position = mesh.mVertices().get(j)
            val texCoord = mesh.mTextureCoords(0)!!.get(j)
            val normal = mesh.mNormals()!!.get(j)
            val color = mesh.mColors(0)!!.get(j)

            vertices.addAll(arrayOf(position.x(), position.y(), position.z()))
            vertices.addAll(arrayOf(texCoord.x(), texCoord.y()))
            vertices.addAll(arrayOf(normal.x(), normal.y(), normal.z()))
            vertices.addAll(arrayOf(color.r(), color.g(), color.b(), color.a()))
        }

        mesh.mFaces().forEach {
            indices.addAll(
                arrayOf(
                    it.mIndices().get(0),
                    it.mIndices().get(1),
                    it.mIndices().get(2)
                )
            )
        }
        return Mesh(attributes, vertices.toFloatArray(), indices.toIntArray())
    }
}