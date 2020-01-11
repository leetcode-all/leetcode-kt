package com.github.alisianoi.problem0007;

// 3928 -> 8293 (11,11,11,11)
// 3968 -> 8693 (11,15,15,11)

// 1221 -> 1221 ( 2, 4, 4, 2)
// 1232 -> 2321 ( 3, 5, 5, 3)

// 2147483647
// 7463847412 -- overflow
// 1214748364 -- if you multiply, it overflows

class Solution {
    fun reverse(x: Int): Int {
        var r = 0
        var y = if (x < 0) -x else x

        while (y != 0) {
            val k = r;
            for (i in 0..8) {
                r += k
                if (r < 0) {
                    return 0;
                }
            }
            r += y % 10;
            y /= 10;
        }

        return if (x < 0) -r else r
    }
}

fun main() {
    println(Solution().reverse(0))
    println(Solution().reverse(-0))
    println(Solution().reverse(1))
    println(Solution().reverse(-1))
    println(Solution().reverse(123))
    println(Solution().reverse(-123))
    println(Solution().reverse(120))
    println(Solution().reverse(Int.MAX_VALUE))
    println(Solution().reverse(Int.MIN_VALUE))
    println(Solution().reverse(1534236469))
}
