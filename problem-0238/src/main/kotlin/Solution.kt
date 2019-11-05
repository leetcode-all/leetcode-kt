package com.github.alisianoi.problem0238;

class Solution {
    fun productExceptSelf(nums: IntArray): IntArray {
        val prefix = MutableList(nums.size) { nums.first() }
        for (i in 1..nums.lastIndex) {
            prefix[i] = prefix[i - 1] * nums[i]
        }

        val suffix = MutableList(nums.size) { nums.last() }
        for (i in nums.lastIndex - 1 downTo 0) {
            suffix[i] = suffix[i + 1] * nums[i]
        }

        val output = MutableList(nums.size) { 1 }
        for (i in 0..nums.lastIndex) {
            when (i) {
                0 -> output[i] = suffix[i + 1]
                nums.lastIndex -> output[i] = prefix[i - 1]
                else -> output[i] = prefix[i - 1] * suffix[i + 1]
            }
        }

        return output.toIntArray()
    }
}

fun main() {
    for (result in Solution().productExceptSelf(intArrayOf(1, 2, 0))) {
        print("$result ")
    }
}
