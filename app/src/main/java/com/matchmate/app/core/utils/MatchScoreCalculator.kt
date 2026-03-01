package com.matchmate.app.core.utils

import com.matchmate.app.domain.model.Person

object MatchScoreCalculator {
    fun calculateMatchScore(person1: Person, person2: Person): Int {
        var score = 0

        // Age difference
        val ageDifference = kotlin.math.abs(person1.age - person2.age)
        score += when {
            ageDifference <= 5 -> 30
            ageDifference <= 10 -> 20
            else -> 10
        }

        // Religion match
        if (person1.religion == person2.religion) {
            score += 30
        }

        // Education match
        if (person1.education == person2.education) {
            score += 20
        }

        // Location match
        if (person1.city == person2.city && person1.country == person2.country) {
            score += 20
        }

        return score.coerceAtMost(100)
    }
}