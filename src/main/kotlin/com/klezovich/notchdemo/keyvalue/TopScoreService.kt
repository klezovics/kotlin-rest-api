package com.klezovich.notchdemo.keyvalue

import java.util.Collections
import java.util.SortedSet
import java.util.TreeSet
import java.util.concurrent.ConcurrentHashMap
import kotlin.streams.toList

class TopScoreService {

    companion object {
        val TOP_RESULT_COUNT = 20L
    }

    // Instead of storing just scores you also want to store the level data
    val topUserMap = ConcurrentHashMap<Int, SortedSet<Int>>()

    // Instead of storing just scores you want to also store user data
    val topLevelMap = ConcurrentHashMap<Int, SortedSet<Int>>()

    fun setInfo(userId: Int, levelId: Int, score: Int) {
        updateUserInfo(userId, score)
        updateLevelInfo(levelId, score)
    }

    fun getLevelTop(levelId: Int): List<Int> {
        val pq = topLevelMap[levelId]
        if (pq == null) return emptyList()

        return pq.stream().limit(TOP_RESULT_COUNT).toList()
    }

    fun getUserTop(userId: Int): List<Int> {
        val pq = topUserMap[userId]
        if (pq == null) return emptyList()

        return pq.stream().limit(TOP_RESULT_COUNT).toList()
    }

    fun updateUserInfo(userId: Int, score: Int) {
        val topUserScores = topUserMap.get(userId)
        if (topUserScores == null) {
            val pq = TreeSet<Int>(Collections.reverseOrder())
            pq.add(score)
            topUserMap.put(userId, pq)
        } else {
            topUserScores.add(score)
        }
    }

    fun updateLevelInfo(levelId: Int, score: Int) {
        val topUserScores = topLevelMap.get(levelId)
        if (topUserScores == null) {
            val pq = TreeSet<Int>(Collections.reverseOrder())
            pq.add(score)
            topLevelMap.put(levelId, pq)
        } else {
            topUserScores.add(score)
        }
    }
}
