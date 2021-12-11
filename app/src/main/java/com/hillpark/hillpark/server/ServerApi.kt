package com.hillpark.hillpark.server

import com.hillpark.hillpark.server.Response.balance.BalanceResponse
import com.hillpark.hillpark.server.Response.person.PersonResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ServerApi {
    @GET("srv/Baloon/Person/GetCurrent")
    fun getProfile() : Single<Response<PersonResponse>>

    @GET("srv/Baloon/Identifier/CurrentIdentifierListGet_FE")
    fun getCardsList() : Single<Response<BalanceResponse>>

    @GET("srv/Baloon/IdentifierInvoice/RaiseInvoice_FE")
    fun getPayUrl(@Query("code")card: String, @Query("amount")amount: String,@Query("fail_url")fail_url: String,@Query("success_url")success_url: String) : Single<PersonResponse>

    @POST("/srv/Baloon/Person/UpdateCurrent")
    fun updateProfile(@Query("last_name")last_name: String,
                      @Query("first_name")first_name: String,
                      @Query("middle_name")middle_name: String,
                      @Query("birthdate")birthday: String,
                      @Query("gender")gender: String,
                      @Query("phone")phone: String) : Single<Response<BalanceResponse>>

    @GET("srv/Baloon/Identifier/GetBalance_FE")
    fun getCardBalance(@Query("code")code: String) : Single<BalanceResponse>

    @GET("srv/Baloon/SpdServiceRule/SpdServiceRuleListGet_API")
    fun getServices(@Query("identifier_rulename")rulename : String) : Single<BalanceResponse>

    @POST("srv/Baloon/Transaction/SalePackage_API?code=s:val&categoryname=s:cat")
    fun buyService(@Query("code")code: String, @Query("categoryname")categoryName: String) : Single<Response<BalanceResponse>>

    @POST("srv/Baloon/Identifier/BindIdentifierToCurrentPerson_FE")
    fun addCard(@Query("code")code: String) : Single<Response<BalanceResponse>>

    @GET("srv/Baloon/Identifier/GetDetails_API")
    fun getCardInfo(@Query("code")code: String) : Single<Response<BalanceResponse>>

    @POST("srv/Baloon/Identifier/IdentifierNameUpdate_FE")
    fun renameCard(@Query("code")code: String, @Query("name")name: String) : Single<Response<BalanceResponse>>

    @GET("srv/Baloon/Transaction/IdentifierTransactionListGet_FE")
    fun getCardOperations(@Query("code")code: String) : Single<Response<BalanceResponse>>

    @GET("srv/Baloon/PackageOrder/MyPackageListGet_API")
    fun getCardOrders(@Query("code")code: String) : Single<Response<BalanceResponse>>

    @GET("srv/Baloon/ExternalResource/ExternalResourceListGet_API")
    fun getCameras() : Single<Response<BalanceResponse>>

    @POST("/srv/Baloon/Identifier/Cancel_FE")
    fun deleteCard(@Query("code")code: String) : Single<Response<BalanceResponse>>

    @GET("/srv/Basicsite/WeatherInfo/WeatherInfoGetLast_FE")
    fun getWeather() : Single<Response<BalanceResponse>>

    @GET("/srv/Baloon/Advertisement/VisualItemListGet_FE_1_13")
    fun getActions() : Single<Response<BalanceResponse>>

    @GET("/srv/Baloon/PaidServiceCategory/PaidServiceCategoryListGet_FE")
    fun getTariffCategories(): Single<Response<BalanceResponse>>

    @GET("/srv/Baloon/PaidService/PaidServiceListGet_API")
    fun getTariffPrices(@Query("categoryid")id: String) : Single<Response<BalanceResponse>>

    @GET("/srv/Baloon/Advertisement/VisualItemListGet_FE_1_13")
    fun loadSlider() : Single<Response<BalanceResponse>>
}