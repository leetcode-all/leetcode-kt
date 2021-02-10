package com.github.alisianoi.problem0042

class SolutionWithPrefix {
    fun trap(heights: IntArray): Int {
        val n = heights.size

        if (n == 0) {
            return 0
        }

        val lftMaxHeights = IntArray(n) { 0 }
        val rgtMaxHeights = IntArray(n) { 0 }

        var lftMaxHeight = heights[0]
        var rgtMaxHeight = heights[n - 1]

        for (i in 0 until n) {
            lftMaxHeight = Integer.max(lftMaxHeight, heights[i])
            lftMaxHeights[i] = lftMaxHeight
        }

        for (i in n - 1 downTo 0) {
            rgtMaxHeight = Integer.max(rgtMaxHeight, heights[i])
            rgtMaxHeights[i] = rgtMaxHeight
        }

        var water = 0
        for (i in 0 until n) {
            water += Integer.min(lftMaxHeights[i], rgtMaxHeights[i]) - heights[i]
        }

        return water
    }
}
