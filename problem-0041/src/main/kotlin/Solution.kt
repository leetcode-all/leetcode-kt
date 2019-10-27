package com.github.alisianoi.problem0041;

class Solution {
    fun firstMissingPositive(nums: IntArray): Int {
        for (i in 0..nums.lastIndex) {
            while (nums[i] != 0) {
                if (nums[i] < 0 || nums[i] >= nums.size + 1) {
                    nums[i] = 0
                    continue
                }

                if (nums[i] == i + 1) {
                    break
                }

                if (nums[i] < i + 1) {
                    nums[nums[i] - 1] = nums[i]
                    nums[i] = 0
                    continue
                }

                if (nums[i] == nums[nums[i] - 1]) {
                    nums[i] = 0
                } else {
                    nums[nums[i] - 1] = nums[i].also { nums[i] = nums[nums[i] - 1] }
                }
            }
        }

        for (i in 0..nums.lastIndex) {
            if (nums[i] != i + 1) {
                return i + 1
            }
        }

        return nums.size + 1
    }
}

fun main() {
    println(Solution().firstMissingPositive(intArrayOf(2, 2)))
}
