package com.github.alisianoi.problem1601;

import kotlin.Comparator
import kotlin.collections.HashMap

class Node(val end: Int) {
    var total = 1;

    override fun toString(): String {
        return "Node(end: $end, total: $total)"
    }
}

typealias SimpleGraph = HashMap<Int, MutableList<Int>>
typealias Graph = HashMap<Int, MutableList<Node>>

class Solution {
    /**
     * @param n is the number of buildings, numbered 0 to n - 1
     * @param requests the array of requests, each entry is [from, into]
     * @return the maximum number of achievable requests
     */
    fun maximumRequests(n: Int, requests: Array<IntArray>): Int {
        val graph = buildGraph(requests)

        var answer = 0
        var visited = mutableListOf(n)
        for ((node, nodes) in graph.entries) {
            if (visited[node] == 1) {
                continue
            }

//            answer += maximumRequests(graph, visited, node, nodes, graph);
        }

        return answer
    }

    private fun maximumRequests(graph: Graph, visited: MutableList<Int>, node: Int, nodes: MutableList<Node>): Int {
        var answer = 0
        for (end in nodes) {
            if (visited[end.end] == 1) {
                continue
            }
        }

        return answer
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
                .sortedWith(Comparator { lft, rgt -> lft.end.compareTo(rgt.end) })
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
    Solution().maximumRequests(0, arrayOf(intArrayOf(1, 2), intArrayOf(0, 1), intArrayOf(2, 0), intArrayOf(1, 2)))
}
