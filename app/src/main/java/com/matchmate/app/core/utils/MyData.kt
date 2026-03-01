package com.matchmate.app.core.utils

import com.matchmate.app.data.local.entity.PersonEntity
import com.matchmate.app.domain.model.Person

object MyData {
    val myData = Person(
        id = "1",
        fullName = "John Doe",
        age = 30,
        gender = "Male",
        city = "New York",
        country = "USA",
        profileImage = "https://randomuser.me/api/portraits/men/1.jpg",
        religion = Religion.HINDU.name,
        education = Education.BACHELORS.name,
        matchScore = 0,
        status = MatchStatus.PENDING
    )
}