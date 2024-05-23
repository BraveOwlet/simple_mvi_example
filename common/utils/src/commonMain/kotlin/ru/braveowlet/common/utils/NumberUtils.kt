package ru.braveowlet.common.utils

import kotlin.math.pow
import kotlin.random.Random
import kotlin.random.nextInt

fun getRandomStringNumber(
    length: UInt,
): String {
    val checkedLength = length.toInt()
    val maxRange = 10f.pow(checkedLength).toInt()
    var random = Random.nextInt(1..< maxRange).toString()
    repeat(checkedLength - random.length) {
        random = "0$random"
    }
    return random
}
