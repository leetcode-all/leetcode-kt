package com.github.alisianoi.problem0029;

class Solution {
    fun divide(dividend: Int, divisor: Int): Int {
        if (divisor == 0) {
            throw IllegalArgumentException("Failed to divide by zero")
        }

        var (x, y) = listOf(dividend, divisor)
        val negative = (x < 0).xor(y < 0)

        var answer = 0
        x = if (x > 0) -x else x
        y = if (y > 0) -y else y
        while (x <= y) {
            var count = 1
            var total = y
            while (total + total < 0 && x <= total + total) {
                total = total + total
                count = count + count
            }
            x -= total
            answer -= count
        }

        if (negative) {
            return answer
        }
        return if (answer == Int.MIN_VALUE) Int.MAX_VALUE else -answer
    }
}