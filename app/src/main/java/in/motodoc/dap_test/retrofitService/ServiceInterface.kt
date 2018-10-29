package `in`.motodoc.dap_test.retrofitService

import `in`.motodoc.dap_test.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceInterface {
    @POST("dap_test/index.php")
    fun registerUser(@Body newUser: RegisterRequest): Call<RegisterResponse>

    @POST("dap_test/index.php")
    fun loginResponse(@Body login: LoginRequest): Call<LoginResponse>

    @POST("dap_test/index.php")
    fun getDescriptionList(@Body userDescription: userDescrtptionRequest): Call<userDescrtptionResponse>


    @POST("dap_test/index.php")
    fun getDescriptionListBox(@Body userListForAssign: UserListForAssignRequest): Call<UserListForAssignResponse>

    @POST("dap_test/index.php")
    fun insertDescription(@Body insertDescriptionRequest: InsertDescriptionRequest): Call<InsertDescriptionResponse>


}