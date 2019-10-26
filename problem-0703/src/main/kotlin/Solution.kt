package com.github.alisianoi.problem0703;

class KthLargest(k: Int, nums: IntArray) {
    private val numbers = IntArray(k) { Int.MIN_VALUE }

    init {
        for (number in nums) {
            add(number)
        }
    }

    fun add(value: Int): Int {
        if (value > numbers[0]) {
            numbers[0] = value
            sink(numbers, 0)
        }

        return numbers[0]
    }

    private fun sink(numbers: IntArray, index: Int) {
        if (index < 0 || index >= numbers.size / 2) {
            return
        }

        var i = index

        do {
            val leftChild = 2 * i + 1
            val rightChild = leftChild + 1

            val child = if (rightChild != numbers.size && numbers[rightChild] < numbers[leftChild]) {
                rightChild
            } else {
                leftChild
            }

            if (numbers[i] <= numbers[child]) {
                break
            }

            numbers[i] = numbers[child].also { numbers[child] = numbers[i] }

            i = child
        } while (i < numbers.size / 2)
    }
}

fun main() {
    val k = 3;
    val numbers = intArrayOf(1, 2, 3);
    val kthLargest = KthLargest(k, numbers);
    println(kthLargest.add(4));   // returns 4
    println(kthLargest.add(5));   // returns 5
    println(kthLargest.add(6));  // returns 5
    println(kthLargest.add(7));   // returns 8
    println(kthLargest.add(8));   // returns 8
}
