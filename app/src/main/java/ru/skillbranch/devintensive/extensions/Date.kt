package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy") : String {
    val dateFormat = SimpleDateFormat (pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND) : Date {
    var time = this.time

    time += when (units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int) : String {
        when(this) {
            SECOND -> return when(value) {
                1, 21, 31, 41, 51, 61 -> "$value секунду"
                2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54, 62, 63, 64 -> "$value секунды"
                else -> "$value секунд"
            }
            MINUTE -> return when(value) {
                1, 21, 31, 41, 51, 61 -> "$value минуту"
                2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54, 62, 63, 64 -> "$value минуты"
                else -> "$value минут"
            }
            HOUR -> return when(value) {
                1, 21 -> "$value час"
                2, 3, 4, 22, 23, 24 -> "$value часа"
                else -> "$value часов"
            }
            DAY -> return when(value) {
                1, 21, 31, 41, 51, 61, 71, 81, 91, 101, 121, 131, 141, 151, 161, 171, 181, 191, 201,
                221, 231, 241, 251, 261, 271, 281, 291, 301, 321, 331, 341, 351, 361, 371 -> "$value день"

                2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54, 62, 63, 64, 72, 73, 74, 82, 83, 84,
                92, 93, 94, 102, 103, 104, 122, 123, 124, 132, 133, 134, 142, 143, 144, 152, 153, 154,
                162, 163, 164, 172, 173, 174, 182, 183, 184, 192, 193, 194, 202, 203, 204, 222, 223, 224,
                232, 233, 234, 242, 243, 244, 252, 253, 254, 262, 263, 264, 272, 273, 274, 282, 283, 284,
                292, 293, 294, 302, 303, 304, 322, 323, 324, 332, 333, 334, 342, 343, 344, 352, 353, 354,
                362, 363, 364 -> "$value дня"

                else -> "$value дней"
            }
        }
    }
}