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

fun Date.humanizeDiff(date: Date = Date()): String {
    val realTime = date.time
    val inputTime = this.time
    val diff = realTime - inputTime
    if (diff > 0) {
        if (diff > 360 * DAY)
            return "более года назад"
        else if (diff < 360 * DAY && diff > 26 * HOUR)
            if (diff / DAY < 5)
                return "${diff / DAY} дня назад"
            else
                return "${diff / DAY} дней назад"
        else if (diff < 26 * HOUR && diff > 22 * HOUR)
            return "день назад"
        else if (diff < 22 * HOUR && diff > 75 * MINUTE)
            if (diff / HOUR < 5)
                return "${diff / HOUR} часа назад"
            else
                return "${diff / HOUR} часов назад"
        else if (diff < 75 * MINUTE && diff > 45 * MINUTE)
            return "час назад"
        else if (diff < 45 * MINUTE && diff > 75 * SECOND)
            if (diff / MINUTE < 5)
                return "${diff / MINUTE} минуты назад"
            else
                return "${diff / MINUTE} минут назад"
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
            if ((-1) * diff / MINUTE < 5)
                return "через ${(-1) * diff / MINUTE} минуты"
            else
                return "через ${(-1) * diff / MINUTE} минут"
        else if (diff > (-1) * 75 * MINUTE && diff < (-1) * 45 * MINUTE)
            return "через час"
        else if (diff > (-1) * 22 * HOUR && diff < (-1) * 75 * MINUTE)
            if ((-1) * diff / HOUR < 5)
                return "через ${(-1) * diff / HOUR} часа"
            else
                return "через ${(-1) * diff / HOUR} часов"
        else if (diff > (-1) * 26 * HOUR && diff < (-1) * 22 * HOUR)
            return "будет завтра"
        else if (diff > (-1) * 360 * DAY && diff < (-1) * 26 * HOUR)
            if ((-1) * diff / DAY < 5)
                return "через ${(-1) * diff / DAY} дня"
            else
                return "через ${(-1) * diff / DAY} дней"
        else
            return "более чем через год"
        }
    }