package com.github.alisianoi.problem0001;

class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val table = HashMap<Int, Int>()

        for (i in nums.indices) {
            val result = table[target - nums[i]]
            if (result != null) {
                return intArrayOf(result, i)
            }

            table[nums[i]] = i
        }

        throw RuntimeException("Failed to find solution")
    }
}