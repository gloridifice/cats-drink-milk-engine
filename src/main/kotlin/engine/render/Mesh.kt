package engine.render

class Mesh(
    val attributes: Array<VertexAttribute>,
    val vertices: FloatArray,
    val indices: IntArray
) {
    fun load(): MeshInstance = MeshInstance(this)
}