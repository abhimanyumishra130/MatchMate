package com.matchmate.app.domain.repository

import com.matchmate.app.core.utils.Result
import com.matchmate.app.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun fetchData(): Flow<Result<List<Person>>>



}