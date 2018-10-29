package `in`.motodoc.dap_test.model

data class Register(val name:String,
                    val email: String,
                    val password: String,
                    val role: Int)

data class RegisterRequest(val operation: String, val newUser: Register)

data class RegisterResponse(val result: String, val message: String)

data class LoginRequest(val operation: String, val login: LoginDetails)

data class LoginResponse(val message: String, val result: String, val loginDetails: LoginResponseDetails)


data class LoginDetails(val email: String, val password: String)

data class LoginResponseDetails(val email: String, val role: String)


data class userDescrtptionRequest(val operation: String, val userDescription: UserDescription)

data class UserDescription(val email: String)

data class userDescrtptionResponse(val result: String, val message: String, val descriptionList:List<DescriptionList>)


data class DescriptionList(val description:String)


data class UserListForAssign(val email: String)

data class UserListForAssignResponse(val result: String, val message: String, val listForAssign: MutableList<ListForAssign>)

data class UserListForAssignRequest(val operation: String, val userListForAssign: UserListForAssign)

data class ListForAssign(val name: String,val email: String, val checkValue: Int)


data class InsertDescriptionRequest(val operation:String,val insertData: InsertDescription)

data class InsertDescription(val description: String, val insertDescription: List<ListForAssign>)

data class InsertDescriptionResponse(val result: String)