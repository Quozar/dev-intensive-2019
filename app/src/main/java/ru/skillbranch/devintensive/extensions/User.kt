package ru.skillbranch.devintensive

import java.util.*

fun User.toUserView() : UserView{

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

fun Date.humanizeDiff(): String {
    val time: Int = Date().format("HH").toInt()
    val lastVisit: Int = this.format("HH").toInt()
    return "Последний раз был в сети: ${time - lastVisit} назад"
}

