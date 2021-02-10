package com.github.alisianoi.problem0480;

import java.util.*

class Solution {
    data class Element(val value: Int, val index: Int)

    val smallElements = TreeSet<Element> { x, y ->
        val comparison = x.value.compareTo(y.value)
        if (comparison == 0) x.index.compareTo(y.index) else comparison
    }

    val largeElements = TreeSet<Element> { x, y ->
        val comparison = x.value.compareTo(y.value)
        if (comparison == 0) x.index.compareTo(y.index) else comparison
    }

    fun medianSlidingWindow(nums: IntArray, k: Int): DoubleArray {
        val answer = DoubleArray(nums.size - k + 1)

        for (i in 0 until k) {
            insert(nums, i)
        }

        answer[0] = median()

        for (i in k until nums.size) {
            remove(i, k)
            insert(nums, i)

            answer[i - k + 1] = median()
        }

        return answer
    }

    private fun median(): Double {
        if (smallElements.isEmpty() && largeElements.isEmpty()) {
            throw RuntimeException("Cannot compute median of an empty window!")
        }

        if (smallElements.size == largeElements.size) {
            return (smallElements.last().value.toLong() + largeElements.first().value.toLong()).toDouble() / 2
        }
        if (smallElements.size + 1 == largeElements.size) {
            return largeElements.first().value.toDouble()
        }
        if (largeElements.size + 1 == smallElements.size) {
            return smallElements.last().value.toDouble()
        }

        throw RuntimeException("Cannot compute median: element groups differ too much!")
    }

    private fun insert(nums: IntArray, idx: Int) {
        if (smallElements.isEmpty() && largeElements.isEmpty()) {
            smallElements.add(Element(nums[idx], idx))
        } else if (smallElements.size == largeElements.size) {
            if (nums[idx] > largeElements.first().value) {
                largeElements.add(Element(nums[idx], idx))
            } else {
                smallElements.add(Element(nums[idx], idx))
            }
        } else if (smallElements.size + 1 == largeElements.size) {
            if (nums[idx] > largeElements.first().value) {
                smallElements.add(largeElements.pollFirst()!!)
                largeElements.add(Element(nums[idx], idx))
            } else {
                smallElements.add(Element(nums[idx], idx))
            }
        } else if (largeElements.size + 1 == smallElements.size) {
            if (nums[idx] < smallElements.last().value) {
                largeElements.add(smallElements.pollLast()!!)
                smallElements.add(Element(nums[idx], idx))
            } else {
                largeElements.add(Element(nums[idx], idx))
            }
        } else {
            throw RuntimeException("Failed to insert a new element: already unbalanced")
        }
    }

    private fun remove(idx: Int, k: Int) {
        val smallCandidate = smallElements.find { element -> element.index == idx - k }
        if (smallCandidate != null) {
            smallElements.remove(smallCandidate)
        }
        val largeCandidate = largeElements.find { element -> element.index == idx - k }
        if (largeCandidate != null) {
            largeElements.remove(largeCandidate)
        }

        if (smallElements.size + 2 == largeElements.size) {
            smallElements.add(largeElements.pollFirst()!!)
        }

        if (largeElements.size + 2 == smallElements.size) {
            largeElements.add(smallElements.pollLast()!!)
        }
    }
}

fun main() {
    val solution = Solution()
    val answer = solution.medianSlidingWindow(intArrayOf(5, 5, 8, 1, 4, 7, 1, 3, 8, 4), 8)
    println(answer.joinToString(", "))

}
