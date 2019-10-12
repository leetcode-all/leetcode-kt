package com.github.alisianoi.problem0017;

class Solution {
    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) {
            return emptyList()
        }

        var answers = listOf("")

        val keys = mapOf(
            '1' to "",
            '2' to "abc",
            '3' to "def",
            '4' to "ghi",
            '5' to "jkl",
            '6' to "mno",
            '7' to "pqrs",
            '8' to "tuv",
            '9' to "wxyz"
        )

        for (digit in digits) {
            val temp = mutableListOf<String>()

            for (answer in answers) {
                for (key in keys.getValue(digit)) {
                    temp.add(answer + key)
                }
            }

            answers = temp.toList()
        }

        return answers
    }
}

fun main() {
    println(Solution().letterCombinations("111"))
}
