package com.matchmate.app.domain.repository

import com.matchmate.app.core.utils.Response
import com.matchmate.app.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun fetchData(): Flow<Response<List<Person>>>



}