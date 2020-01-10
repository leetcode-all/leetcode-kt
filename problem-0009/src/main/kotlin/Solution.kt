package com.github.alisianoi.problem0009;

// 0000
// MAX: 2 ^ 4 - 1 = 15
// 1111 = 1 + 2 + 4 + 8 = 15


// 0000 = 0
// 0001 = 1
// ...
// 0111 = 7
// 1000 = -8
// 1001 = -7
// 1010 = -6
// 1011 = -5
// ...
// 1111 = -1

class Solution {
    fun isPalindrome(x: Int): Boolean {
        if (x < 0) {
            return false
        }
        if (x != 0 && x % 10 == 0) {
            return false
        }

        var a = x
        var b = 0

        while (b < a) {
            val r = a % 10;

            a /= 10

            if (a == b) {
                return true
            }

            b *= 10;
            b += r;
        }

        if (a == b) {
            return true
        }

        return false
    }
}

fun main() {
    println(Solution().isPalindrome(0))
}
