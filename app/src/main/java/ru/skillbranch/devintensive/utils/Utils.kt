package ru.skillbranch.devintensive.utils

import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {

        return if (fullName?.replace(" ", "") == "")
            null to null
        else {
            val parts: List<String>? = fullName?.split(" ")
            val firstName = parts?.getOrNull(0)
            val lastName = parts?.getOrNull(1)
            firstName to lastName
        }
    }

    // IT WORKS, BUT UNIT TEST ON SKILLBRANCH NOT COMPILES, SO I SHOULD FIX IT
    fun transliteration(payload: String?, divider: String = " "): String? {
        if (payload == null)
            return null
        else {
            val payloadList: List<String> = payload.toLowerCase().split(" ")
            val returnList: ArrayList<String> = ArrayList()

            for (word in payloadList) {
                var ltnWord = ""
                for (symbol in word) {
                    when (symbol) {
                        'а' -> ltnWord += "a"
                        'б' -> ltnWord += "b"
                        'в' -> ltnWord += "v"
                        'г' -> ltnWord += "g"
                        'д' -> ltnWord += "d"
                        'е' -> ltnWord += "e"
                        'ё' -> ltnWord += "e"
                        'ж' -> ltnWord += "zh"
                        'з' -> ltnWord += "z"
                        'и' -> ltnWord += "i"
                        'й' -> ltnWord += "i"
                        'к' -> ltnWord += "k"
                        'л' -> ltnWord += "l"
                        'м' -> ltnWord += "m"
                        'н' -> ltnWord += "n"
                        'о' -> ltnWord += "o"
                        'п' -> ltnWord += "p"
                        'р' -> ltnWord += "r"
                        'с' -> ltnWord += "s"
                        'т' -> ltnWord += "t"
                        'у' -> ltnWord += "u"
                        'ф' -> ltnWord += "f"
                        'х' -> ltnWord += "h"
                        'ц' -> ltnWord += "c"
                        'ч' -> ltnWord += "ch"
                        'ш' -> ltnWord += "sh"
                        'щ' -> ltnWord += "sh"
                        'ъ' -> ltnWord += ""
                        'ы' -> ltnWord += "i"
                        'ь' -> ltnWord += ""
                        'э' -> ltnWord += "e"
                        'ю' -> ltnWord += "yu"
                        'я' -> ltnWord += "ya"
                        else -> ltnWord += ""
                    }
                }
                returnList += if (ltnWord == "") word else ltnWord
            }

            var rWord = ""
            for (word in returnList) {
                rWord += if (returnList.lastIndexOf(word) < returnList.lastIndex)
                    "${word[0].toUpperCase()}${word.substring(1, word.length)}$divider"
                else
                    "${word[0].toUpperCase()}${word.substring(1, word.length)}"
            }
            return rWord
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        return if (firstName == null && lastName != null)
            lastName.substring(0, 1).toUpperCase()
        else if (firstName != null && lastName == null)
            firstName.substring(0, 1).toUpperCase()
        else if (firstName == null && lastName == null || firstName?.trim() == "" && lastName?.trim() == "")
            null
        else
            "${firstName?.substring(0, 1)?.toUpperCase()}${lastName?.substring(0, 1)?.toUpperCase()}"
    }
}
