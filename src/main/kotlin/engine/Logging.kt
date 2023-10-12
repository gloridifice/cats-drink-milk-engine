package engine

import com.github.ajalt.mordant.rendering.TextColors.*
import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger { }
fun info(message: () -> Any?){
    logger.info { white(message().toString()) }

}
fun warn(message: () -> Any?){
    logger.warn { yellow(message().toString()) }
}
