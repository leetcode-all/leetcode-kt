package com.github.alisianoi.problem0795;

class Solution {
    fun numSubarrayBoundedMax(A: IntArray, L: Int, R: Int): Int {
        val markers = MutableList(A.size) { 0 }

        for (i in 0..A.lastIndex) {
            markers[i] = when {
                A[i] < L -> 0
                A[i] <= R -> 1
                else -> 2
            }
        }

        var nilCount = 0
        var oneCount = 0
        var total = 0
        for (i in 0..markers.lastIndex) {
            when {
                markers[i] == 0 -> {
                    nilCount++;
                    oneCount++;
                }
                markers[i] == 1 -> {
                    total -= nilCount * (nilCount + 1) / 2
                    nilCount = 0;
                    oneCount++;
                }
                else -> {
                    total -= nilCount * (nilCount + 1) / 2
                    total += oneCount * (oneCount + 1) / 2
                    nilCount = 0;
                    oneCount = 0;
                }
            }
        }

        total -= nilCount * (nilCount + 1) / 2
        total += oneCount * (oneCount + 1) / 2

        return total
    }
}

fun main() {
    println("Hello, world!")
}
