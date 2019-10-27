package com.github.alisianoi.problem0034;

class Solution {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        val lb = lowerBound(nums, target)
        val rb = upperBound(nums, target)

        val la = if (lb == nums.size || nums[lb] != target) {
            -1
        } else {
            lb
        }
        val ra = if (la == -1) {
            -1
        } else {
            rb - 1
        }

        return intArrayOf(la, ra)
    }

    private fun lowerBound(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) {
            return nums.size
        }

        var (lft, rgt) = 0 to nums.size

        do {
            val mid = lft + (rgt - lft) / 2

            when {
                target <= nums[mid] -> rgt = mid
                else                -> lft = mid + 1
            }
        } while (lft < rgt)

        return lft
    }

    private fun upperBound(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) {
            return nums.size
        }

        var (lft, rgt) = 0 to nums.size

        do {
            val mid = lft + (rgt - lft) / 2

            when {
                target >= nums[mid] -> lft = mid + 1
                else                -> rgt = mid
            }
        } while (lft < rgt)

        return lft
    }
}

fun main() {
    println(Solution().searchRange(intArrayOf(1, 2, 3), 0).contentToString())
    println(Solution().searchRange(intArrayOf(1, 2, 3), 8).contentToString())
    println(Solution().searchRange(intArrayOf(0, 1, 1, 1, 2), 1).contentToString())
    println(Solution().searchRange(intArrayOf(1, 1, 1), -42).contentToString())
    println(Solution().searchRange(intArrayOf(1, 1, 1), 42).contentToString())
}
