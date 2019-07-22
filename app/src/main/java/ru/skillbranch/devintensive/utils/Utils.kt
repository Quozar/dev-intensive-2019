package ru.skillbranch.devintensive

import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object Utils {
    fun parseFullName(fullName:String): Pair<String?, String?>{
        // FIX IT
        val parts : List<String>? = fullName?.split(" ")

        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)

        if(firstName == "" || firstName == null && lastName == null)
            return null to null
        else
            return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {

        val table: HashMap<String, String> = HashMap()
        table.put("а", "a")
        table.put("б", "b")
        table.put("в", "v")
        table.put("г", "g")
        table.put("д", "d")
        table.put("е", "e")
        table.put("ё", "e")
        table.put("ж", "zh")
        table.put("з", "z")
        table.put("и", "i")
        table.put("й", "i")
        table.put("к", "k")
        table.put("л", "l")
        table.put("м", "m")
        table.put("н", "n")
        table.put("о", "o")
        table.put("п", "p")
        table.put("р", "r")
        table.put("с", "s")
        table.put("т", "t")
        table.put("у", "u")
        table.put("ф", "f")
        table.put("х", "h")
        table.put("ц", "c")
        table.put("ч", "ch")
        table.put("ш", "sh")
        table.put("щ", "sh")
        table.put("ъ", "")
        table.put("ы", "i")
        table.put("ь", "")
        table.put("э", "e")
        table.put("ю", "yu")
        table.put("я", "ya")

        val payloadList: List<String> = payload.split(" ")
        var returnList: ArrayList<String> = ArrayList()

        for(word in payloadList){
            var lWord = ""  // Слово на Латинице

            for(symbol in 0 until word.length){
                if(table.containsKey(word.substring(symbol, symbol + 1).toLowerCase())) // Повышение регистра первой буквы
                    if(symbol == 0) {
                        lWord += table.get(word.substring(symbol, symbol + 1).toLowerCase())
                        if(lWord.length > 1){   // Если первая буква Кирилицы превратилась в ДВЕ буквы латиницы
                            var begin: String = lWord.substring(0, 1)
                            begin = begin.toUpperCase()
                            var end: String = lWord.substring(1, lWord.length)
                            lWord = begin + end
                        }else
                            lWord = lWord.toUpperCase()
                    }
                    else
                        lWord += table.get(word.substring(symbol, symbol + 1).toLowerCase())
                else {
                    returnList.add(word)
                    break
                }
            }
            if(lWord != "")
                returnList.add(lWord)
        }

        var transliteratedName = ""
        for(word in returnList){
            transliteratedName += word + divider
        }

        return transliteratedName.substring(0, transliteratedName.length - 1)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if(firstName == null && lastName != null)
            return "${lastName?.substring(0, 1).toUpperCase()}"
        if(firstName != null && lastName == null)
            return "${firstName?.substring(0, 1).toUpperCase()}"
        if(firstName == null && lastName == null || firstName?.trim() == "" && lastName?.trim() == "")
            return null
        else
            return "${firstName?.substring(0, 1)?.toUpperCase()}${lastName?.substring(0, 1)?.toUpperCase()}"
    }
}