package com.github.alisianoi.problem0012;

class Solution {
    fun intToRoman(num: Int): String {
        var result = String()
        var number = num

        for (level in 0..3) {
            if (number == 0) {
                break
            }

            val (fst: String, mid: String, lst: String) = when (level) {
                0 -> Triple("I", "V", "X")
                1 -> Triple("X", "L", "C")
                2 -> Triple("C", "D", "M")
                3 -> Triple("M", "M", "M")
                else -> throw RuntimeException("unknown level $level")
            }

            val digit = number % 10;
            number /= 10;

            result = when (digit) {
                0 -> result
                1 -> "$fst$result"
                2 -> "$fst$fst$result"
                3 -> "$fst$fst$fst$result"
                4 -> "$fst$mid$result"
                5 -> "$mid$result"
                6 -> "$mid$fst$result"
                7 -> "$mid$fst$fst$result"
                8 -> "$mid$fst$fst$fst$result"
                9 -> "$fst$lst$result"
                else -> throw RuntimeException("unknown digit $digit")
            }
        }

        return result
    }
}

fun main() {
    //                      40 XL     90 XC
    //   1  I      11 XI    41 XLI    91 XCI
    //   2  II     12 XII   42 XLII
    //   3  III    13 XIII  43 XLIII
    //   4  IV     14 XIV   44 XLIV
    //   5  V      15 XV    45 XLV
    //   6  VI     16 XVI   46 XLVI
    //   7  VII    17 XVII  47 XLVII
    //   8  VIII   18 XVIII 48 XLVIII
    //   9  IX     19 XIX   49 XLIX
    //  10  X      20 XX    50 L

    // 101  CI
    // 301
    // 321  CCCXXI
    // 3221 MMMCCXXI

    // I             1
    // V             5
    // X             10
    // L             50
    // C             100
    // D             500
    // M             1000

    // I can be placed before V (5) and X (10) to make 4 and 9.
    // X can be placed before L (50) and C (100) to make 40 and 90.
    // C can be placed before D (500) and M (1000) to make 400 and 900.

    // 1 to 3999

    val solution = Solution()
    println(solution.intToRoman(45))
}
