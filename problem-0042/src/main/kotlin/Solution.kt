package com.github.alisianoi.problem0042;

class Solution {
    fun trap(heights: IntArray): Int {
        if (heights.isEmpty() || heights.size == 1) {
            return 0
        }

        val elevations =
            heights.mapIndexed { index, height -> index to height }.sortedWith(compareByDescending { it.second })

        var (left, right) = if (elevations[0].first < elevations[1].first) {
            elevations[0] to elevations[1]
        } else {
            elevations[1] to elevations[0]
        }
        var water = elevations[1].second * (right.first - left.first - 1)

        for (i in 2..elevations.lastIndex) {
            when {
                elevations[i].first < left.first -> {
                    water += elevations[i].second * (left.first - elevations[i].first - 1)

                    left = elevations[i]
                }
                elevations[i].first > right.first -> {
                    water += elevations[i].second * (elevations[i].first - right.first - 1)

                    right = elevations[i]
                }
                else -> water -= elevations[i].second
            }
        }

        return water
    }
}

fun main() {
    println(Solution().trap(intArrayOf(3, 2, 1, 1, 2, 3)))
}
