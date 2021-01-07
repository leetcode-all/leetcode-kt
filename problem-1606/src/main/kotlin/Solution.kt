package com.github.alisianoi.problem1606;

import java.util.*

//3
// [3,4,6,8,9,11,12,16]
// [1,2,8,6,5, 3, 8, 3]

// Expected:
// [1]

data class Server(val i: Int, val timeWhenFree: Int)

class Solution {

    private val pq = PriorityQueue<Server> { lft, rgt ->
        val time = lft.timeWhenFree.compareTo(rgt.timeWhenFree)

        if (time == 0) {
            lft.i.compareTo(rgt.i)
        } else {
            time
        }
    }

    fun busiestServers(k: Int, arrival: IntArray, load: IntArray): List<Int> {
        for (i in 0 until k) {
            pq.add(Server(i, 0))
        }

        val requestDone = MutableList(k) { 0 }

        for (i in arrival.indices) {
            val idx = i % k
            val arrivalTime = arrival[i]
            val durationTime = load[i]

            var bestChoice: Server? = null
            val servers = mutableListOf<Server>()

            do {
                val nextChoice = pq.poll()

                println("poll: ${nextChoice.i}")

                if (nextChoice.timeWhenFree > arrivalTime) {
                    pq.add(nextChoice)
                    break
                }

                if (bestChoice == null) {
                    bestChoice = nextChoice
                } else if (nextChoice.i == idx || (nextChoice.i < idx && bestChoice.i < idx || nextChoice.i > idx && bestChoice.i > idx) && nextChoice.i < bestChoice.i || (bestChoice.i < idx && nextChoice.i > idx)) {
                    servers.add(bestChoice)
                    bestChoice = nextChoice
                } else {
                    servers.add(nextChoice)
                }

                if (bestChoice?.i == idx) {
                    break
                }
            } while (pq.isNotEmpty())

            if (bestChoice != null) {
                println("Will do request $i on server ${bestChoice.i}")
                requestDone[bestChoice.i] += 1
                pq.add(Server(bestChoice.i, arrivalTime + durationTime))
            }

            for (server in servers) {
                println("add ${server.i}")
                pq.add(server)
            }

            println()
        }

        val bestTotal = requestDone.max()

        val answer = mutableListOf<Int>()
        for (i in requestDone.indices) {
            if (requestDone[i] == bestTotal) {
                answer.add(i)
            }
        }

        return answer
    }
}


//13
//[1,3,5,6,7,8,13,15,18,20,21,22,23,26,28,33,35,38,39,43,46,47,49,51,55,56,58,59,60,62,67,69,72,73,75,79,80,81,82,83,84,85,86,87,89,90,92,93,96]
//[13,12,39,5,40,43,44,18,13,19,9,42,34,25,1,13,44,23,46,3,30,1,48,47,11,12,5,46,2,41,2,4,49,10,36,36,21,18,46,20,31,41,18,15,17,7,22,40,11]

// Expected:
// [8]

fun main() {
    println("${Solution().busiestServers(
        13
        , listOf(1,3,5,6,7,8,13,15,18,20,21,22,23,26,28,33,35,38,39,43,46,47,49,51,55,56,58,59,60,62,67,69,72,73,75,79,80,81,82,83,84,85,86,87,89,90,92,93,96).toIntArray()
        , listOf(13,12,39,5,40,43,44,18,13,19,9,42,34,25,1,13,44,23,46,3,30,1,48,47,11,12,5,46,2,41,2,4,49,10,36,36,21,18,46,20,31,41,18,15,17,7,22,40,11).toIntArray())}"
    )
}
