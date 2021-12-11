package com.hillpark.hillpark.server

import com.hillpark.hillpark.server.Response.ResultResponse
import com.hillpark.hillpark.server.Response.person.PersonResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApi {
    @POST("auth/login")
    fun login(@Query("Login")login: String, @Query("Password")password: String) : Single<Response<ResultResponse>>

    @GET("/nologin/srv/Basicsite/AuthConfirmationToken/SubmitRestorePassword")
    fun restorePassword(@Query("login")login: String) : Single<Response<PersonResponse>>

    @GET("/nologin/srv/Baloon/Person/RegisterNewClient_API")
    fun register(
        @Query("regcode")regcode: String?,
        @Query("email")email: String,
        @Query("password")password: String,
        @Query("password2")password2: String,
        @Query("phone")phone: String?,
        @Query("first_name")first_name: String?,
        @Query("last_name")last_name: String?
    ) : Single<Response<PersonResponse>>
}