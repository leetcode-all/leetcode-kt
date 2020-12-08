package com.github.alisianoi.problem1605;

import kotlin.math.min

// if it's a 1 by 1, then easy
// if it's a 1 by n, then easy
// if it's a n by 1, then easy
// let's say we have an n - 1 by m - 1 ready
// can we transition to n by m?
class Solution {
    fun restoreMatrix(rowSum: IntArray, colSum: IntArray): Array<IntArray> {
        var rowIdx = 0
        var colIdx = 0
        val matrix = Array(rowSum.size) { IntArray(colSum.size) }

        while (rowIdx != rowSum.size && colIdx != colSum.size) {
            val choice = min(rowSum[rowIdx], colSum[colIdx])

            matrix[rowIdx][colIdx] = choice

            rowSum[rowIdx] -= choice
            colSum[colIdx] -= choice

            if (rowSum[rowIdx] == 0) {
                ++rowIdx
            }
            if (colSum[colIdx] == 0) {
                ++colIdx
            }
        }

        return matrix;
    }
}

fun main() {
    val matrix = Solution().restoreMatrix(intArrayOf(1, 2, 3, 4), intArrayOf(5, 4, 1))

    for (row in matrix) {
        for (element in row) {
            print("$element ")
        }
        println()
    }
}
