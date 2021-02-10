package com.github.alisianoi.problem0042

class SolutionTwoPointers {
    fun trap(heights: IntArray): Int {
        if (heights.isEmpty()) {
            return 0
        }

        var water = 0
        var (lftIdx, rgtIdx) = listOf(0, heights.size - 1)
        var (lftMax, rgtMax) = listOf(heights.first(), heights.last())

        while (lftIdx < rgtIdx) {
            if (lftMax < rgtMax) {
                water += lftMax - heights[lftIdx]
                lftIdx++
                lftMax = Integer.max(lftMax, heights[lftIdx])
            } else {
                water += rgtMax - heights[rgtIdx]
                rgtIdx--
                rgtMax = Integer.max(rgtMax, heights[rgtIdx])
            }
        }

        return water
    }
}