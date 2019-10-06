package com.github.alisianoi.problem0005;

class Solution {
    fun longestPalindrome(s: String): String {
        if (s.isBlank()) {
            return ""
        }

        for (length in s.length downTo 2) {
            for (i in 0..s.length - length) {
                if (isPalindrome(s, i, i + length - 1)) {
                    return s.slice(i until i + length)
                }
            }
        }

        return s.slice(0..0);
    }

    private fun isPalindrome(s: String, lft: Int, rgt: Int): Boolean {
        var fst = lft;
        var lst = rgt;

        while (fst <= lst) {
            if (s[fst] != s[lst]) {
                return false
            }

            fst++;
            lst--;
        }

        return true;
    }
}

fun main() {
    val solution = Solution()
    println(solution.longestPalindrome("aba"))
}
