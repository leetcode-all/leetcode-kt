package com.github.alisianoi.problem0015;

class Solution {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val numbers = nums.sorted()
        val answers = mutableListOf<List<Int>>()

        var i = 0

        while (i != numbers.size) {

            var j = i + 1
            var k = numbers.lastIndex

            while (j < k) {
                val sum = numbers[i] + numbers[j] + numbers[k]

                when {
                    sum < 0 -> do {
                        ++j;
                    } while (j < k && numbers[j - 1] == numbers[j])
                    sum > 0 -> do {
                        --k;
                    } while (j < k && numbers[k] == numbers[k + 1])
                    else -> {
                        answers.add(listOf(numbers[i], numbers[j], numbers[k]))

                        do {
                            ++j;
                        } while (j < k && numbers[j - 1] == numbers[j])

                        do {
                            --k;
                        } while (j < k && numbers[k] == numbers[k + 1])
                    }
                }
            }

            do {
                ++i
            } while (i != numbers.size && numbers[i - 1] == numbers[i])
        }

        return answers
    }
}

fun main() {
    // [-5, 4, 3, 2, 1]
    // [3, 2, 1, -4] => [-4, 1, 3]
    // [-4, 1, 2, 3] => [-4, 1, 3]

    // [-5, -4, -4, -3, 1, 2, 2, 3, 7, 8] => [-5, -3, 8], [-4, -4, 8], [-5, 2, 3], [-4, 2, 2]

    // n (n - 1) / 2 sums
    val solution = Solution()
    println(solution.threeSum(intArrayOf(-5, -4, -4, -3, 1, 2, 2, 3, 7, 8)))
}
