package com.github.alisianoi.problem0022;

class Solution {
    fun generateParenthesis(n: Int): List<String> {
        if (n == 0) {
            return emptyList()
        }
        // 0 -> []
        // 1 -> ["()"]
        // 2 -> ["(())", "()()"]
        // 3 -> ["((()))", "(()())", "(())()", "()(())", "()()()"]
        return generateParenthesis(n, listOf("("), 1, 1)
    }

    private fun generateParenthesis(n: Int, results: List<String>, open: Int, nowOpen: Int): List<String> {
        if (open == n && nowOpen == 0) {
            return results
        }

        val answers = mutableListOf<String>()

        if (open != n) {
            answers.addAll(generateParenthesis(n, results.map { "$it(" }, open + 1, nowOpen + 1))
        }

        if (nowOpen != 0) {
            answers.addAll(generateParenthesis(n, results.map { "$it)" }, open, nowOpen - 1))
        }

        return answers
    }
}

fun main() {
    println(Solution().generateParenthesis(1))
}
