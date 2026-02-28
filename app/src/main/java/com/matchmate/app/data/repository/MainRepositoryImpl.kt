package com.matchmate.app.data.repository

import com.matchmate.app.core.utils.Response
import com.matchmate.app.data.local.dao.MainDao
import com.matchmate.app.data.local.database.MainDatabase
import com.matchmate.app.data.mapper.toPersonList
import com.matchmate.app.data.service.MainService
import com.matchmate.app.domain.model.Person
import com.matchmate.app.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(val service: MainService, val database: MainDao): MainRepository {
    override suspend fun fetchData(): Flow<Response<List<Person>>> =
        flow{
            emit(Response.Loading)
            val cachedData = database.fetchData()
            if (cachedData.isNotEmpty()){
                emit(Response.Success(cachedData.toPersonList()))
            }

            val fetchAllData = service.fetchData(20)
            emit(fetchAllData.results)

        }


}