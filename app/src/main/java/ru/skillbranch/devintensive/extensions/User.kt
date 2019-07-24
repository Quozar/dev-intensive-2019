package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

fun User.toUserView() : UserView {

    val nickname = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName, lastName)
    val status = if(lastVisit == null) "Ещё ни разу не был" else if(isOnline) "online" else "Последний раз был ${lastVisit.humanizeDiff()}"
    return UserView(
            id,
            fullName = "$firstName $lastName",
            nickName = nickname,
            initials = initials,
            avatar = avatar,
            status = status)
}

fun TimeUnits.plural(value: Int) : String {
    when(this) {
        TimeUnits.SECOND -> return when(value) {
            1, 21, 31, 41, 51, 61 -> "$value секунду"
            2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54, 62, 63, 64 -> "$value секунды"
            else -> "$value секунд"
        }
        TimeUnits.MINUTE -> return when(value) {
            1, 21, 31, 41, 51, 61 -> "$value минуту"
            2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54, 62, 63, 64 -> "$value минуты"
            else -> "$value минут"
        }
        TimeUnits.HOUR -> return when(value) {
            1, 21 -> "$value час"
            2, 3, 4, 22, 23, 24 -> "$value часа"
            else -> "$value часов"
        }
        TimeUnits.DAY -> return when(value) {
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

fun Date.humanizeDiff(date: Date = Date()): String {
    val realTime = date.time
    val inputTime = this.time
    val diff = realTime - inputTime
    if (diff > 0) {
        if (diff > 360 * DAY)
            return "более года назад"
        else if (diff < 360 * DAY && diff > 26 * HOUR)
            return "${TimeUnits.DAY.plural((diff / DAY).toInt())} назад"
        else if (diff < 26 * HOUR && diff > 22 * HOUR)
            return "день назад"
        else if (diff < 22 * HOUR && diff > 75 * MINUTE)
            return "${TimeUnits.HOUR.plural((diff / HOUR).toInt())} назад"
        else if (diff < 75 * MINUTE && diff > 45 * MINUTE)
            return "час назад"
        else if (diff < 45 * MINUTE && diff > 75 * SECOND)
            return "${TimeUnits.MINUTE.plural((diff / MINUTE).toInt())} назад"
        else if (diff < 75 * SECOND && diff > 45 * SECOND)
            return "минуту назад"
        else if (diff < 45 * SECOND && diff > SECOND)
            return "несколько секунд назад"
        else return "только что"
    } else {
        if (diff > (-1) * 45 * SECOND && diff < (-1) * SECOND)
            return "через несколько секунд"
        else if (diff > (-1) * 75 * SECOND && diff < (-1) * 45 * SECOND)
            return "через минуту"
        else if (diff > (-1) * 45 * MINUTE && diff < (-1) * 75 * SECOND)
            return "через ${TimeUnits.MINUTE.plural(((-1) * diff / MINUTE).toInt())}"
        else if (diff > (-1) * 75 * MINUTE && diff < (-1) * 45 * MINUTE)
            return "через час"
        else if (diff > (-1) * 22 * HOUR && diff < (-1) * 75 * MINUTE)
            return "через ${TimeUnits.HOUR.plural(((-1) * diff / HOUR).toInt())}"
        else if (diff > (-1) * 26 * HOUR && diff < (-1) * 22 * HOUR)
            return "будет завтра"
        else if (diff > (-1) * 360 * DAY && diff < (-1) * 26 * HOUR)
            return "через ${TimeUnits.DAY.plural(((-1) * diff / DAY).toInt())}"
        else
            return "более чем через год"
        }
    }