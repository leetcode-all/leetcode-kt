package com.github.alisianoi.problem0231;

class Solution {
    fun isPowerOfTwo(n: Int): Boolean {
        if (n <= 0) {
            return false
        }

        if (n == 1) {
            return true
        }

        val remainders = intArrayOf(2, 4, 6, 8)

        var number = n
        while (number != 1) {
            if (!remainders.contains(number.rem(10))) {
                return false
            }

            number /= 2
        }

        return true
    }
}

fun main() {
    println(Solution().isPowerOfTwo(65536))
}
