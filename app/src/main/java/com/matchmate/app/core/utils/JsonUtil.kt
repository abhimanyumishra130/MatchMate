package com.matchmate.app.core.utils

import com.google.gson.Gson

object JsonUtil {
    val gson = Gson()

    inline fun <reified T> String.fromJson(): T {
        return gson.fromJson(this, T::class.java)
    }

    inline fun <reified T> T.toJson(): String {
        return gson.toJson(this)
    }
}