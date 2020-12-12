package com.github.alisianoi.problem1601;

class Node(val end: Int) {
    var total = 1;

    override fun toString(): String {
        return "Node(end: $end, total: $total)"
    }
}

typealias SimpleGraph = HashMap<Int, MutableList<Int>>
typealias Graph = HashMap<Int, MutableList<Node>>

// 1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 2048
// [[1,1],[1,0],[0,1],[0,0],[0,0],[0,1],[0,1],[1,0],[1,0],[1,1],[0,0],[1,0]]
// [[1,0],[0,1],[0,1],[0,1],[1,0],[1,0],[1,0]] + 5
class Solution {
    /**
     * @param n is the number of buildings, numbered 0 to n - 1
     * @param requests the array of requests, each entry is [from, into]
     * @return the maximum number of achievable requests
     */
    fun maximumRequests(n: Int, requests: Array<IntArray>): Int {
        var granted = 0

        for (mask in 0 until fastPow(2, requests.size)) {
            var total = 0
            var (i, m) = listOf(0, mask)
            val counts = MutableList(n) { 0 }

            while (m != 0) {
                if (m % 2 == 1) {
                    val (from, into) = requests[i]

                    counts[from]--
                    counts[into]++

                    total++
                }

                i++
                m /= 2
            }

            println(mask)
            if (mask == 3071) {
                println(counts)
                println(granted)
                println(total)
            }

            if (counts.find {count -> count != 0} == null && total > granted) {
                granted = total
            }
        }

        return granted
    }

    fun fastPow(base: Int, exponent: Int): Int {
        if (exponent < 0) {
            throw IllegalArgumentException("exponent must be non-negative")
        }

        if (exponent == 0) {
            return 1
        }

        var x = 1
        var b = base
        var n = exponent

        while (n != 1) {
            if (n % 2 == 1) {
                x *= b
            }
            b *= b
            n /= 2
        }

        return x * b
    }

    fun fastPow0(base: Int, exponent: Int): Int {
        if (exponent == 0) {
            return 1
        }

        if (exponent == 1) {
            return base
        }

        return if (exponent % 2 == 0) {
            fastPow0(base * base, exponent / 2)
        } else {
            base * fastPow0(base * base, exponent / 2)
        }
    }

    fun depthFirstSearch(n: Int, edges: Array<IntArray>) {
        val graph = buildSimpleGraph(edges)
        depthFirstSearch(n, graph, MutableList(graph.size) { 0 })
    }

    private fun depthFirstSearch(n: Int, simpleGraph: SimpleGraph, visited: MutableList<Int>) {
        visited[n] = 1
        println("Enter $n")
        for (next in simpleGraph[n]!!) {
            if (visited[next] == 1) {
                continue
            }

            depthFirstSearch(next, simpleGraph, visited)
        }
        println("Leave $n")
    }

    private fun buildGraph(edges: Array<IntArray>): Graph {
        val graph = Graph()
        for (edge in edges) {
            graph.putIfAbsent(edge[0], mutableListOf())
            graph.putIfAbsent(edge[1], mutableListOf())

            graph[edge[0]]!!.add(Node(edge[1]))
            graph[edge[1]]!!.add(Node(edge[0]))
        }

        graph.forEach { (node, nodes) ->
            graph[node] = nodes
                .sortedWith { lft, rgt -> lft.end.compareTo(rgt.end) }
                .fold(mutableListOf(), { xs, x ->
                    if (xs.isNotEmpty() && xs.last().end == x.end) {
                        xs.last().total++
                    } else {
                        xs.add(x)
                    }

                    xs
                })
        }

        return graph
    }

    private fun buildSimpleGraph(edges: Array<IntArray>): SimpleGraph {
        val graph = SimpleGraph()
        for (edge in edges) {
            graph.putIfAbsent(edge[0], mutableListOf())
            graph.putIfAbsent(edge[1], mutableListOf())

            graph[edge[0]]!!.add(edge[1])
            graph[edge[1]]!!.add(edge[0])
        }

        return graph
    }
}

fun main() {
    for (i in 0..15) {
        println("$i ${Solution().fastPow(2, i)}")
    }
//    println(
//        Solution().maximumRequests(
//            2,
//            arrayOf(intArrayOf(1,1),intArrayOf(1,0),intArrayOf(0,1),intArrayOf(0,0),intArrayOf(0,0),intArrayOf(0,1),intArrayOf(0,1),intArrayOf(1,0),intArrayOf(1,0),intArrayOf(1,1),intArrayOf(0,0),intArrayOf(1,0))
//        )
//    )
}
