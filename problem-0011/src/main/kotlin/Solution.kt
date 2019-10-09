package com.github.alisianoi.problem0011;

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Solution {
    fun maxArea(heights: IntArray): Int {
        if (heights.isEmpty()) {
            return 0
        }

        val xs = heights
            .mapIndexed { i, height -> i + 1 to height }
            .sortedBy { item -> item.second }

        val ys = xs.foldRightIndexed(MutableList(heights.size) { 0 to 1 }, { index, pair, acc ->
                if (index == heights.lastIndex) {
                    acc[index] = pair.first to pair.first
                } else {
                    acc[index] = max(pair.first, acc[index + 1].first) to min(pair.first, acc[index + 1].second)
                }

                acc
            })

        return xs.subList(0, xs.lastIndex).foldIndexed(0, {i, acc, pair ->
            max(acc, pair.second * max(abs(pair.first - ys[i + 1].first), abs(pair.first - ys[i + 1].second)))
        })
    }
}

fun main() {
    val solution = Solution()
    println(solution.maxArea(intArrayOf(6, 5, 4, 3, 2, 1)))
}
