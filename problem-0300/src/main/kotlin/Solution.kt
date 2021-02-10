package com.github.alisianoi.problem0300;

class Solution {

    private var memo: MutableList<MutableList<Int>> = mutableListOf()

    fun lengthOfLIS(nums: IntArray): Int {
        memo = MutableList(nums.size + 1) { MutableList(nums.size) { 0 } }

        val result = lengthOfLIS(nums, nums.size, 0)

        for (i in memo.indices) {
            println("${memo[i]}")
        }

        return result
    }

    private fun lengthOfLIS(nums: IntArray, prev: Int, next: Int): Int {
        if (next == nums.size) {
            return 0
        }

        if (memo[prev][next] != 0) {
            return memo[prev][next]
        }

        val candidate = if (prev == nums.size || nums[prev] < nums[next]) {
            val takeNext = 1 + lengthOfLIS(nums, next, next + 1)
            val dropNext = 0 + lengthOfLIS(nums, prev, next + 1)

            if (dropNext > takeNext) dropNext else takeNext
        } else {
            lengthOfLIS(nums, prev, next + 1)
        }

        memo[prev][next] = if (candidate > memo[prev][next]) candidate else memo[prev][next]

        return memo[prev][next]
    }
}

fun main() {
    println("${Solution().lengthOfLIS(intArrayOf(3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12))}")
}
