package com.matchmate.app.core.utils

enum class Religion {
    HINDU,
    MUSLIM,
    CHRISTIAN,
    SIKH,
    BUDDHIST,
    JAIN,
    OTHER;
    companion object{
        fun random(): Religion {
            val values = Religion.entries.toTypedArray()
            return values.random()
        }
    }
}