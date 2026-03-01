package com.matchmate.app.data.mapper

import com.matchmate.app.core.utils.Education
import com.matchmate.app.core.utils.MatchStatus
import com.matchmate.app.core.utils.Religion
import com.matchmate.app.data.local.entity.PersonEntity
import com.matchmate.app.data.local.entity.User
import com.matchmate.app.domain.model.Person


fun PersonEntity.toPerson(): Person {
    return Person(
        id,fullName,age,gender,city,country,profileImage,religion,education,matchScore,status
    )
}

fun List<PersonEntity>.toPersonList(): List<Person> {
    return this.map { it.toPerson() }
}

fun User.toPersonEntity(): PersonEntity{
    return PersonEntity(
        id = login.uuid,

        fullName =
            "${name.first} ${name.last}",

        age = dob.age,
        gender = gender,

        city = location.city,
        country = location.country,

        profileImage = picture.large,

        religion = Religion.random().name,
        education = Education.random().name,

        matchScore = 0,
        status = MatchStatus.PENDING
    )
}

fun List<User>.toPersonEntityList(): List<PersonEntity> {
    return this.map { it.toPersonEntity() }
}

fun Person.toPersonEntity(): PersonEntity {
    return PersonEntity(
        id,fullName,age,gender,city,country,profileImage,religion,education,matchScore,status
    )
}

