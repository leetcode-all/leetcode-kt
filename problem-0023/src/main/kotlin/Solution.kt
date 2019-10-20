package com.github.alisianoi.problem0023;

class ListNode(var value: Int) {
    var next: ListNode? = null

    override fun toString(): String {
        return "ListNode($value)"
    }
}

class Solution {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        heapify(lists)

        if (lists.isEmpty() || lists[0] == null) {
            return null
        }

        val head = lists[0]
        var tail = lists[0]

        do {
            lists[0] = lists[0]?.next
            sink(lists, 0, lists.size)

            tail?.next = lists[0]

            tail = tail?.next
        } while (lists[0] != null)

        return head
    }

    private fun heapify(lists: Array<ListNode?>) {
        for (i in lists.size / 2 downTo 0) {
            sink(lists, i, lists.size)
        }
    }

    private fun sink(lists: Array<ListNode?>, index: Int, length: Int) {
        if (index < 0 || index >= length / 2) {
            return
        }

        var i = index

        do {
            val lchild = 2 * i + 1
            val rchild = lchild + 1

            val child = when {
                rchild == length -> lchild
                lists[lchild] == null -> rchild
                lists[rchild] == null -> lchild
                lists[lchild]?.value!! <= lists[rchild]?.value!! -> lchild
                else -> rchild
            }

            if (lists[child] == null || (lists[i] != null && lists[i]?.value!! <= lists[child]?.value!!)) {
                return
            }

            lists[i] = lists[child].also { lists[child] = lists[i] }

            i = child
        } while (i < length / 2)
    }
}

fun main() {
    val node22 = ListNode(22)
    val node25 = ListNode(25)

    val node10 = ListNode(10)
    val node15 = ListNode(15)
    val node31 = ListNode(31)

    val node40 = ListNode(10)
    val node45 = ListNode(15)
    val node61 = ListNode(31)

    node40.next = node45
    node45.next = node61

    node10.next = node15
    node15.next = node31

    node22.next = node25

    var head = Solution().mergeKLists(arrayOf(node22, node10, node40))

    while (head != null) {
        println(head)

        head = head.next
    }
}
