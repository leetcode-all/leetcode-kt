package com.github.alisianoi.problem0863;

class TreeNode(var `val`: Int = 0) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

// Incomplete binary tree, K = 4
//                            1
//              2                           3
//       4.            5.            6;               7
//   08     09     10     11     12     13      14          15
// 16  17 18  19 20  21 22  23 24  25 26  27 28.   29.   30.   31.
// Value of upCount only matters if answer is not empty!
data class BinaryTreeInfo(val upCount: Int, val answer: List<Int>);

class Solution {
    fun distanceK(root: TreeNode?, target: TreeNode?, K: Int): List<Int> {
        return distanceK(root, target, K, 0).answer
    }

    fun distanceK(root: TreeNode?, target: TreeNode?, K: Int, D: Int): BinaryTreeInfo {
        if (root == null || target == null) {
            return BinaryTreeInfo(0, emptyList())
        }

        if (root.`val` == target.`val`) {
            return BinaryTreeInfo(1, distanceDownFrom(target, K))
        }

        val answer = mutableListOf<Int>()
        // One of these must be empty, which one?
        val lAnswer = distanceK(root.left, target, K, D + 1)
        val rAnswer = distanceK(root.right, target, K, D + 1)

        answer.addAll(lAnswer.answer)
        answer.addAll(rAnswer.answer)

        val upCount = 1 + if (lAnswer.answer.isNotEmpty()) lAnswer.upCount else rAnswer.upCount

        if (lAnswer.answer.isNotEmpty() && root.right != null && lAnswer.upCount <= K) {
            answer.addAll(distanceDownFrom(root.right!!, K - lAnswer.upCount))
        }
        if (rAnswer.answer.isNotEmpty() && root.left != null && rAnswer.upCount <= K) {
            answer.addAll(distanceDownFrom(root.left!!, K - rAnswer.upCount))
        }

        return BinaryTreeInfo(upCount, answer)
    }

    fun distanceDownFrom(node: TreeNode, K: Int): List<Int> {
        if (K == 0) {
            return listOf(node.`val`)
        }

        val answer = mutableListOf<Int>()
        if (node.left != null) {
            answer.addAll(distanceDownFrom(node.left!!, K - 1))
        }
        if (node.right != null) {
            answer.addAll(distanceDownFrom(node.right!!, K - 1))
        }
        return answer
    }
}

// Incomplete binary tree, K = 4
//                            1
//              2                           3
//       4.            5.            6;               7
//   08     09     10     11     12     13      14          15
// 16  17 18  19 20  21 22  23 24  25 26  27 28.   29.   30.   31.
fun main() {
    println("Hello, world!")
}
