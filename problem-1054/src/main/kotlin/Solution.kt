package com.github.alisianoi.problem1054;

fun <T> sink(xs: MutableList<T>, index: Int, comparator: Comparator<T>) where T : Comparable<T> {
    if (index < 0 || index > xs.size && index != 0) {
        throw IllegalArgumentException("index is bad")
    }

    var (i, lftChild) = listOf(index, 2 * index + 1)
    while (lftChild < xs.size) {
        val rgtChild = lftChild + 1

        val child = if (rgtChild < xs.size && comparator.compare(xs[rgtChild], xs[lftChild]) < 0) {
            rgtChild
        } else {
            lftChild
        }

        if (comparator.compare(xs[i], xs[child]) <= 0) {
            break
        }

        xs[i] = xs[child].also { xs[child] = xs[i] }

        i = child; lftChild = 2 * i + 1
    }
}

fun <T> swim(xs: MutableList<T>, index: Int, comparator: Comparator<T>) where T : Comparable<T> {
    if (index < 0 || index > xs.size && index != 0) {
        throw IllegalArgumentException("index is bad")
    }

    var i = index
    while (i != 0) {
        val parent = (i - 1) / 2

        if (comparator.compare(xs[parent], xs[i]) <= 0) {
            break
        }

        xs[i] = xs[parent].also { xs[parent] = xs[i] }

        i = parent
    }
}

fun <T> heapify(xs: MutableList<T>, comparator: Comparator<T>) where T : Comparable<T> {
    for (i in xs.size / 2 downTo 0) {
        sink(xs, i, comparator)
    }
}

fun <T> pop(xs: MutableList<T>, comparator: Comparator<T>): T where T : Comparable<T> {
    if (xs.isEmpty()) {
        throw IllegalArgumentException("xs is empty!")
    }

    if (xs.size == 1) {
        return xs.removeAt(xs.lastIndex)
    }

    val answer = xs[0].also { xs[0] = xs.removeAt(xs.lastIndex) }

    sink(xs, 0, comparator)

    return answer
}

data class Box(var count: Int, var value: Int) : Comparable<Box> {
    override fun compareTo(other: Box): Int {
        val valueResult = this.value.compareTo(other.value)

        return if (valueResult == 0) {
            this.count.compareTo(other.count)
        } else {
            valueResult
        }
    }
}

class Solution {
    fun rearrangeBarcodes(barcodes: IntArray): IntArray {
        barcodes.sort()

        val xs = mutableListOf<Box>()
        var (lft, rgt) = listOf(0, 0)
        while (rgt != barcodes.size) {
            while (rgt != barcodes.size && barcodes[lft] == barcodes[rgt]) {
                rgt += 1
            }

            xs.add(Box(rgt - lft, barcodes[lft]))

            lft = rgt
        }
        heapify(xs, compareByDescending { it.count })

        val ys = mutableListOf<Int>()
        while (xs.isNotEmpty()) {
            val fst = pop(xs, compareByDescending { it.count })

            if (xs.isEmpty()) {
                if (fst.count != 1) {
                    throw RuntimeException("oh no")
                }

                ys.add(fst.value)

                break
            }

            val snd = pop(xs, compareByDescending { it.count })

            ys.add(fst.value)
            ys.add(snd.value)

            fst.count -= 1
            snd.count -= 1

            if (fst.count != 0) {
                xs.add(fst)
                swim(xs, xs.lastIndex, compareByDescending { it.count })
            }
            if (snd.count != 0) {
                xs.add(snd)
                swim(xs, xs.lastIndex, compareByDescending { it.count })
            }
        }

        return ys.toIntArray()
    }
}

fun main() {
    Solution().rearrangeBarcodes(intArrayOf(1, 1, 2, 2, 2, 2, 2, 4, 4, 3, 4, 3)).forEach { v -> print("$v ") }
}
