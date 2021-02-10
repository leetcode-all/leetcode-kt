package com.github.alisianoi.problem0042

import java.util.*

class SolutionWithStack {
    fun trap(heights: IntArray): Int {
        var (idx, water) = listOf(0, 0)
        val stack = ArrayDeque<Int>()

        while (idx != heights.size) {
            if (stack.isEmpty()) {
                stack.addLast(idx)
                idx++;
                continue;
            }

            val midIdx = stack.removeLast()
            if (heights[midIdx] >= heights[idx]) {
                stack.addLast(midIdx)
                stack.addLast(idx)
                idx++;
                continue
            }

            if (stack.isEmpty()) {
                continue
            }
            val lftIdx = stack.removeLast()
            water += (Integer.min(heights[lftIdx], heights[idx]) - heights[midIdx]) * (idx - lftIdx - 1)
            stack.addLast(lftIdx)
        }

        return water
    }
}
