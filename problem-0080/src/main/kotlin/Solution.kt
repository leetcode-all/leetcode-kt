package com.github.alisianoi.problem0080;

class Solution {
    fun removeDuplicates(nums: IntArray): Int {
        var i = 0
        var j = 0
        var k = 0

        while (k != nums.size) {
            while (k != nums.size && nums[j] == nums[k]) {
                ++k
            }

            for (x in 0..1) {
                nums[i] = nums[j]
                ++i
                ++j

                if (j == k) {
                    break
                }
            }

            j = k
        }

        return i
    }
}

fun main() {
    println(Solution().removeDuplicates(intArrayOf(1, 2, 3)))
}
