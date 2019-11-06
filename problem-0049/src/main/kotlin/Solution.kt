package com.github.alisianoi.problem0049;

class Solution {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        // [Pair(42, Pair("aet", "ate"))]
        val xs =
            strs.mapIndexed { index, element -> index to (element.toCharArray().sorted().joinToString(separator = "") to element) }
                .toList().sortedWith(compareBy { it.second.first })

        var i = 0
        var j = 0
        val ys = MutableList(0) { listOf<String>() }
        while (i != xs.size) {
            while (j != xs.size && xs[i].second.first == xs[j].second.first) {
                ++j
            }

            ys.add(xs.subList(i, j).map { it.second.second })

            i = j
        }

        return ys
    }
}

fun main() {
    println(Solution().groupAnagrams(arrayOf("ate", "tea", "top")))
}
