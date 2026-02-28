package com.matchmate.app.core.utils

enum class Education {
    HIGH_SCHOOL,
    BACHELORS,
    MASTERS,
    DOCTORATE,
    OTHER;
    companion object{
        fun random(): Education {
            val values = Education.entries.toTypedArray()
            return values.random()
        }
    }
}