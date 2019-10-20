package com.github.alisianoi.problem0027;

class Solution {
    fun removeElement(nums: IntArray, value: Int): Int {
        var toRemove = 0
        var toRemain = nums.size - 1

        while (toRemove <= toRemain) {
            while (toRemove <= toRemain && nums[toRemove] != value) {
                ++toRemove
            }
            while (toRemove <= toRemain && nums[toRemain] == value) {
                --toRemain
            }

            if (toRemove <= toRemain) {
                nums[toRemove] = nums[toRemain].also { nums[toRemain] = nums[toRemove] }
            }
        }

        return toRemain + 1
    }
}

fun main() {
    val xs = intArrayOf(3, 1, 3, 2, 3, 3)

    println(Solution().removeElement(xs, 4))

    for (x in xs) {
        print("$x ")
    }
}
