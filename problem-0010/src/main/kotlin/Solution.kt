package com.github.alisianoi.problem0010;

class Solution {
    /**
     * s is string, p is regex
     */
    fun isMatch(s: String, p: String): Boolean {
        return isMatch(s, p, 0, 0)
    }

    private fun isMatch(s: String, p: String, si: Int, pi: Int): Boolean {
        if (p.length == pi) return s.length == si

        val firstMatch: Boolean = s.length != si && (p[pi] == '.' || p[pi] == s[si])

        return if (p.length - pi >= 2 && p[pi + 1] == '*') {
            isMatch(s, p, si, pi + 2) || (firstMatch && isMatch(s, p, si + 1, pi + 2))
        } else {
            firstMatch && isMatch(s, p, si + 1, pi + 1)
        }
    }
}

fun main() {
    println(Solution().isMatch("a", "c*a"))
}
