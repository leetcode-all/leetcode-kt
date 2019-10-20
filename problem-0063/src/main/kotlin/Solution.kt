package com.github.alisianoi.problem0063;

class Solution {
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        val X = obstacleGrid.size

        if (X == 0) {
            return 0
        }

        val Y = obstacleGrid[0].size

        if (Y == 0) {
            return 0
        }

        val combinationGrid = Array(X) { IntArray(Y) { 0 } }

        combinationGrid[0][0] = 1

        for (x in 0 until X) {
            for (y in 0 until Y) {
                if (obstacleGrid[x][y] == 1) {
                    combinationGrid[x][y] = 0

                    continue
                }

                if (x == 0 && y == 0) {
                    continue
                } else if (x == 0) {
                    combinationGrid[x][y] = combinationGrid[x][y - 1]
                } else if (y == 0) {
                    combinationGrid[x][y] = combinationGrid[x - 1][y]
                } else {
                    combinationGrid[x][y] = combinationGrid[x][y - 1] + combinationGrid[x - 1][y]
                }
            }
        }

        return combinationGrid[X - 1][Y - 1]
    }
}

fun main() {
    println(Solution().uniquePathsWithObstacles(arrayOf(intArrayOf(0))))
}
