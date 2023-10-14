package engine.util

import kotlin.math.PI

fun Float.toRadians(): Float{
    return this / 180f * PI.toFloat()
}
fun Float.toDegrees(): Float{
    return this * 180f / PI.toFloat()
}
