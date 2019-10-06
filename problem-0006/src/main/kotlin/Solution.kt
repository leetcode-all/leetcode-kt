package com.github.alisianoi.problem0006;

import java.lang.IllegalArgumentException
import kotlin.math.min

class Solution() {
    fun convert(s: String, numRows: Int): String {
        if (numRows <= 0) {
            throw IllegalArgumentException("numRows must be >= 1")
        }

        if (s.length == 1 || numRows == 1) {
            return s
        }

        val border = min(s.length, numRows)
        val stringBuilder = StringBuilder(s.length)
        for (i in 0 until border) {
            val bigStep = 2 * (border - 1)
            val smallStep = bigStep - 2 * i

            if (i == 0 || i == border - 1) {
                for (j in i until s.length step bigStep) {
                    stringBuilder.append(s[j])
                }
            } else {
                var j = i;
                do {
                    stringBuilder.append(s[j])
                    if (j + smallStep < s.length) {
                        stringBuilder.append(s[j + smallStep])
                    }

                    j += bigStep
                } while (j < s.length)
            }
        }

        return stringBuilder.toString()
    }
}

fun main() {
    val solution = Solution()
    println(solution.convert("ABCDE", 4))
    // = 2
    // 0  2  4  6
    // 1  3  5

    // = 3
    // 2 * (3 - 1) = 4 // 0     4     8
    // 1  3  5  7  9 11
    // 2     6    10

    // = 4
    //       // 0        6       12
    // 4, 2  // 1     5  7    11 13
    // 2, 4  // 2  4     8 10    14
    //       // 3        9

    // = 5
    // 2 * (5 - 1) = 8 //  0         8          16
    // 6, 2            //  1      7  9       15 17
    // 4, 4            //  2    6   10    14    18
    // 2, 6            //  3  5     11 13
                       //  4        12

    // = 6
              //  0             10             20             30
    // 8, 2  //  1           9 11          19 21          29 31
    // 6, 4  //  2        8    12       18    22       28    32
    // 4, 4  //  3     7       13    17       23    27       33
    // 6, 2  //  4  6          14 16          24 26          34
             //  5             15             25
}