package com.github.alisianoi.problem0010;

class Solution {
    /**
     * s is string, p is regex
     */
    fun isMatch(s: String, p: String): Boolean {
        return isMatch(s, p, 0, 0)
    }

    private fun isMatch(s: String, p: String, si: Int, pi: Int): Boolean {
        if (s.length == si && p.length == pi) {
            return true
        }

        return if (p.length == pi) {
            return false
        } else if (p.length == pi + 1) {
            if (s.length == si) {
                false
            } else {
                (p[pi] == '.' || s[si] == p[pi]) && isMatch(s, p, si + 1, pi + 1)
            }
        } else {
            if (s.length == si) {
                if (p[pi + 1] == '*') {
                    isMatch(s, p, si, pi + 2)
                } else {
                    false
                }
            } else {
                if (p[pi + 1] == '*') {
                    isMatch(s, p, si, pi + 2) || (p[pi] == '.' || s[si] == p[pi]) && isMatch(s, p, si + 1, pi)
                } else if (p[pi] == '.' || s[si] == p[pi]) {
                    isMatch(s, p, si + 1, pi + 1)
                } else {
                    false
                }
            }
        }
    }
}

fun main() {
    println(Solution().isMatch("la", "l*"))
}
