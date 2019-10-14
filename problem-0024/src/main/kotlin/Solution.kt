package com.github.alisianoi.problem0024;

class ListNode(var value: Int) {
    var next: ListNode? = null

    override fun toString(): String {
        return "ListNode($value)"
    }
}

class Solution {
    fun swapPairs(head: ListNode?): ListNode? {
        var newHead: ListNode? = head

        var tmp: ListNode?

        var node0: ListNode? = null
        var node1: ListNode? = null
        var node2: ListNode? = null
        var node3: ListNode? = head

        do {
            if (node3 == null) {
                break
            }

            node0 = node1
            node1 = node2
            node2 = node3
            node3 = node3.next

            if (node3 == null) {
                break
            }

            node0 = node1
            node1 = node2
            node2 = node3
            node3 = node3.next

            if (node0 == null) {
                newHead = node2
            } else {
                node0.next = node2
            }

            node1.next = node3
            node2.next = node1

            tmp = node1
            node1 = node2
            node2 = tmp
        } while(true)

        return newHead
    }
}

fun main() {
//    val node0 = ListNode(1)
//    val node1 = ListNode(2)
//    val node2 = ListNode(3)
//    val node3 = ListNode(4)
//    val node4 = ListNode(5)
//    val node5 = ListNode(6)
//
//    node0.next = node1
//    node1.next = node2
//    node2.next = node3
//    node3.next = node4
//    node4.next = node5

    var head: ListNode? = Solution().swapPairs(null)

    do {
        println(head)

        head = head?.next
    } while(head != null)
}
