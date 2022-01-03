package com.example.uniformcivilnumber

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

class UniformCivilNumber {
    val weights = arrayOf(
        2,
        4,
        8,
        5,
        10,
        9,
        7,
        3,
        6
    )
    val regions = mapOf(
        "Благоевград" to 43,
        "Бургас" to 93,
        "Варна" to 139,
        "Велико Търново" to 169,
        "Видин" to 183,
        "Враца" to 217,
        "Габрово" to 233,
        "Кърджали" to 281,
        "Кюстендил" to 301,
        "Ловеч" to 319,
        "Монтана" to 341,
        "Пазарджик" to 377,
        "Перник" to 395,
        "Плевен" to 435,
        "Пловдив" to 501,
        "Разград" to 527,
        "Русе" to 555,
        "Силистра" to 575,
        "Сливен" to 601,
        "Смолян" to 623,
        "София - град" to 721,
        "София - окръг" to 751,
        "Стара Загора" to 789,
        "Добрич (Толбухин)" to 821,
        "Търговище" to 843,
        "Хасково" to 871,
        "Шумен" to 903,
        "Ямбол" to 925,
        "Друг/Неизвестен" to 999
    )
    val monthsBg = mapOf(
        1 to "януари",
        2 to "февруари",
        3 to "март",
        4 to "април",
        5 to "май",
        6 to "юни",
        7 to "юли",
        8 to "август",
        9 to "септември",
        10 to "октомври",
        11 to "ноември",
        12 to "декември"
    )
    val firstNum = mapOf(
        0 to 43,
        44 to 93,
        94 to 139,
        140 to 169,
        170 to 183,
        184 to 217,
        218 to 233,
        234 to 281,
        282 to 301,
        302 to 319,
        320 to 341,
        342 to 377,
        378 to 395,
        396 to 435,
        436 to 501,
        502 to 527,
        528 to 555,
        556 to 575,
        576 to 601,
        602 to 623,
        624 to 721,
        722 to 751,
        752 to 789,
        790 to 821,
        822 to 843,
        844 to 871,
        872 to 903,
        904 to 925,
        926 to 999
    )

    fun valid(ucn: String): Boolean {
        if (ucn.length != 10) {
            return false
        }
        val year = ucn.substring(0, 2).toInt()
        val mon = ucn.substring(2, 4).toInt()
        val day = ucn.substring(4, 6).toInt()
        if (mon > 40) {
            if (!checkdate(mon - 40, day, year + 2000)) return false
        } else if (mon > 20) {
            if (!checkdate(mon - 20, day, year + 1800)) return false
        } else {
            if (!checkdate(mon, day, year + 1900)) return false
        }
        val checkSum = ucn.substring(9, 10).toInt()
        var uncSum = 0
        for (i in 0 until 9) {
            uncSum += ucn.substring(i, i + 1).toInt() * weights[i]
        }
        var validCheckSum = uncSum % 11
        if (validCheckSum == 10) {
            validCheckSum = 0
        }
        if (validCheckSum == checkSum)
            return true
        return false
    }

    fun checkdate(month: Int, day: Int, year: Int): Boolean {
        if (!(1..32767).contains(year))
            return false
        return try {
            LocalDate.of(year, month, day)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun parse(ucn: String): Map<String, String>? {
        if (!valid(ucn))
            return null
        var ret = mutableMapOf<String, String>()
        ret.set("year", ucn.substring(0, 2))
        ret.set("month", ucn.substring(2, 4))
        ret.set("day", ucn.substring(4, 6))
        val month = ret.get("month")?.toInt() ?: 0
        val year = ret.get("year")?.toInt() ?: 0
        if (month > 40) {
            ret.set("month", (month - 40).toString())
            ret.set("year", (year + 2000).toString())
        } else if (month > 20) {
            ret.set("month", (month - 20).toString())
            ret.set("year", (year + 1800).toString())
        } else {
            ret.set("year", (year + 1900).toString())
        }
        ret.set(
            "birthday_text",
            ret.get("day") + " " + monthsBg.get(month) + " " + ret.get("year") + " г."
        )
        var region = ucn.substring(6, 9).toInt()
        ret.set("region_num", region.toString())
        val sex = ucn.substring(8, 9).toInt() % 2
        ret.set("sex", sex.toString())
        ret.set("sex_text", "жена")
        if (sex == 0) {
            ret.set("sex_text", "мъж")
        }
        var firstRegionNum = 0
        regions.forEach {
            if (ret.get("region_text") == null && region >= firstRegionNum && region <= it.value) {
                ret.set("region_text", it.key)
            } else if (ret.get("region_text") == null) {
                firstRegionNum = (it.value + 1)
            }
        }
        if ((ucn.substring(8, 9).toInt() % 2) != 0) {
            region--
        }
        ret.set("birthnumber", ((region - firstRegionNum) / 2 + 1).toString())
        return ret
    }

    fun info(ucn: String): String {
        if (!valid(ucn)) {
            return " $ucn невалиден ЕГН"
        }
        val data = parse(ucn)
        val sex = data?.get("sex") ?: "0"
        val birthNumber = data?.get("birthnumber")?.toInt() ?: 0
        var ret = " " + ucn + " е ЕГН на " + (data?.get("sex_text") ?: " ") + " "
        ret += "роден" + (if (sex == "1") "a" else "") + " на " + (data?.get("birthday_text")
            ?: "") + " в "
        ret += "регион " + (data?.get("region_text") ?: " ") + " "
        if (birthNumber - 1 > 0) {
            ret += "като преди " + (if (sex == "1") "нея" else "него") + " "
            if (birthNumber - 1 > 1) {
                ret += "в този ден и регион са се родили " + (birthNumber - 1) + " "
                ret += (if (sex == "1") "момичета" else "момчета")
            } else {
                ret += "в този ден и регион се е родило 1"
                ret += (if (sex == "1") "момиче" else "момче")
            }
        } else {
            ret += "като е " + (if (sex == "1") "била" else "бил")
            ret += "първото " + (if (sex == "1") "момиче" else "момче")
            ret += "родено в този ден и регион"
        }
        return ret
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun generate(
        inputDay: Int = 0,
        inputMon: Int = 0,
        inputYear: Int = 0,
        sex: Int = 0,
        inputRegion: Int
    ): String? {
        val day = if (inputDay > 0) {
            Math.min(inputDay, 31)
        } else {
            if (inputDay < 0) {
                0
            } else {
                inputDay
            }
        }

        val mon = if (inputMon > 0) {
            Math.min(inputMon, 12)
        } else {
            if (inputMon < 0) {
                0
            } else {
                inputMon
            }
        }

        val year = if (inputYear > 1799) {
            Math.min(inputYear, 2099)
        } else {
            if (inputYear == 0) {
                inputYear
            } else {
                1800
            }
        }

        val region: Int? = if (firstNum[inputRegion] != null) {
            inputRegion
        } else {
            null
        }

        var iter: Int = 0
        var gday = 0
        var gmon = 0
        var gyear = 0
        do {
            gday = if (day > 0) day else (1..31).random()
            gmon = if (mon > 0) mon else (1..12).random()
            gyear = if (year > 0) year else (1900..2022).random()
            iter++
        } while (!checkdate(gmon, gday, gyear) && iter < 3)

        val cent = gyear - (gyear % 100)
        if (iter > 3)
            return null
        when (cent) {
            1800 -> gmon += 20
            2000 -> gmon += 40
        }
        var gregion = 0
        if (region == null) {
            gregion = (0..999).random()
        } else {
            gregion = (region..firstNum[region]!!).random()
        }
        /* Make it odd */
        if (sex == 1 && (gregion % 2 != 0))
         gregion--
        /* Make it even */
        if (sex == 2 && (gregion % 2 == 0))
         gregion++
        /* Create EGN */
        var egn = (gyear - cent).toString().padStart(2, "0".single()) +
                  gmon.toString().padStart(2, "0".single()) +
                  gday.toString().padStart(2, "0".single()) +
                  gregion.toString().padStart(3, "0".single())

        /* Calculate checksum */
        var egnSum = 0
        for (i in 0 until 9) {
            egnSum += egn.substring(i, i+1).toInt() * weights[i]
        }
        var validCheckSum = egnSum % 11
        if (validCheckSum == 10)
            validCheckSum = 0
        egn += validCheckSum.toString()

        return egn
    }
}