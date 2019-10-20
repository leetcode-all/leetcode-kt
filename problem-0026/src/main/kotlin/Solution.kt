package com.github.alisianoi.problem0026;

class Solution {
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.isEmpty() || nums.size == 1) {
            return nums.size
        }

        var left = 0
        var right = 1

        while (right != nums.size) {
            while (right != nums.size && nums[left] == nums[right]) {
                ++right
            }

            if (right == nums.size) {
                break
            }

            ++left

            if (left != right) {
                nums[left] = nums[right]
            }

            ++right
        }

        return left + 1
    }
}

fun main() {
    println(Solution().removeDuplicates(intArrayOf(1, 1, 1)))
}
