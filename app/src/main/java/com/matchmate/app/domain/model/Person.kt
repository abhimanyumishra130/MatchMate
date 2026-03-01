package com.matchmate.app.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.matchmate.app.core.utils.MatchStatus

data class Person(

    @SerializedName("id") val id: String,

    @SerializedName("fullName") val fullName: String,
    @SerializedName("age") val age: Int,
    @SerializedName("gender") val gender: String,

    @SerializedName("city") val city: String,
    @SerializedName("country") val country: String,

    @SerializedName("profileImage") val profileImage: String?,

    @SerializedName("religion") val religion: String,
    @SerializedName("education") val education: String,

    @SerializedName("matchScore") val matchScore: Int,

    @SerializedName("status") var status: MatchStatus
)