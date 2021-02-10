package com.github.alisianoi.problem1614;

class Solution {

    data class RunMax(var total: Int = 0, var count: Int = 0)

    fun maxDepth(s: String): Int {
        return s.fold(RunMax()) { r, x ->
            if (x == '(') {
                r.count += 1
            }
            if (x == ')') {
                r.count -= 1
            }

            if (r.count > r.total) {
                r.total = r.count
            }

            r
        }.total
    }
}

fun main() {
    println(Solution().maxDepth("(())()"))
}
