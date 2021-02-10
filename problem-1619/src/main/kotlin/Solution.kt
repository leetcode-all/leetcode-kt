package com.github.alisianoi.problem1619;

class Solution {
    fun trimMean(arr: IntArray): Double {
        arr.sort()

        val lft = arr.size / 20
        val rgt = arr.size - lft

        var total: Double = 0.0
        for (i in lft until rgt) {
            total += arr[i]
        }

        return total / (rgt - lft)
    }
}

fun main() {
    println(Solution().trimMean(intArrayOf(1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3)))
    println(Solution().trimMean(intArrayOf(6, 2, 7, 5, 1, 2, 0, 3, 10, 2, 5, 0, 5, 5, 0, 8, 7, 6, 8, 0)))
}
