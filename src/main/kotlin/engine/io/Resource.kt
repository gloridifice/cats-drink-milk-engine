package engine.io

import java.net.URL

class Resource(val resourceType: ResourceType, val name:String){
    val fileName : String = "assets/${resourceType.id}/$name"

    fun getURL() : URL{
        val classLoader = javaClass.classLoader
        val url = classLoader.getResource(fileName) ?: throw IllegalArgumentException("file not found! $fileName")
        return url
    }
    override fun toString(): String {
        return "${resourceType.name} : $name"
    }
    init {

    }
}