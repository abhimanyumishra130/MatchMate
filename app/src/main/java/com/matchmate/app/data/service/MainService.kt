package com.matchmate.app.data.service

import com.matchmate.app.data.local.entity.MatchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {

    @GET("")
    suspend fun fetchData(@Query("result") result: Int): Response<MatchResult>
}