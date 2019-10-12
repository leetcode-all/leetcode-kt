package com.github.alisianoi.problem0016;

import kotlin.math.abs

class Solution {
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        nums.sort()

        var i = 0
        var bestTotal = nums[0] + nums[1] + nums[2];

        while (i != nums.size) {
            var j = i + 1
            var k = nums.lastIndex

            while (j < k) {
                val total = nums[i] + nums[j] + nums[k]

                bestTotal = if (abs(target - total) < abs(target - bestTotal)) total else bestTotal

                when {
                    total < target -> j = increaseToNextUnique(nums, j, k)
                    total > target -> k = decreaseToNextUnique(nums, k, j)
                    else -> return total;
                }
            }

            i = increaseToNextUnique(nums, i, nums.size)
        }

        return bestTotal
    }

    private fun increaseToNextUnique(nums: IntArray, index: Int, border: Int): Int {
        var i = index;

        do {
            ++i;
        } while (i < border && nums[i - 1] == nums[i])

        return i;
    }

    private fun decreaseToNextUnique(nums: IntArray, index: Int, border: Int): Int {
        var i = index;

        do {
            --i;
        } while (i > border && nums[i + 1] == nums[i])

        return i;
    }
}

fun main() {
    val solution = Solution()
    println(solution.threeSumClosest(intArrayOf(-1, 2, 1, -4), 1))
}
