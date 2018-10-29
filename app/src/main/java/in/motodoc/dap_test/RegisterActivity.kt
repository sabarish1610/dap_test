package `in`.motodoc.dap_test

import `in`.motodoc.dap_test.base.BaseActivity
import `in`.motodoc.dap_test.model.Register
import `in`.motodoc.dap_test.model.RegisterRequest
import `in`.motodoc.dap_test.model.RegisterResponse
import `in`.motodoc.dap_test.retrofitService.ApiClient
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_register.*
import android.widget.RadioButton
import android.widget.Toast
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response


class RegisterActivity : BaseActivity() {
    var isAdmin: Int = -1
    var rGroup: RadioGroup? = null
    private var radioSexButton: RadioButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        rGroup = findViewById(R.id.admin_check)
        new_register.setOnClickListener {
            val selectedId = rGroup?.checkedRadioButtonId
            radioSexButton = findViewById(selectedId!!)
            isAdmin = when {
                radioSexButton?.text == "Yes" -> 1
                radioSexButton?.text == "No" -> 0
                else -> -1
            }
            when {
                reg_user_name.text.toString().isEmpty() -> showToast("Enter Name")
                reg_email.text.toString().isEmpty() -> showToast("Enter Email")
                reg_password.text.toString().isEmpty() -> showToast("Enter Password")
                isAdmin == -1 -> showToast("Select User Type")
                else -> registerDetails()
            }
        }

    }

    private fun registerDetails() {
        if (networkCheck(this)) {
            showLoading(this)
            val api = ApiClient().mServiceAPI()

            val register =
                Register(
                    reg_user_name.text.toString(),
                    reg_email.text.toString(),
                    reg_password.text.toString(),
                    isAdmin
                )
            val registerRequest = RegisterRequest("userRegisteration", register)

            Log.d(
                "mmm request",
                Gson().toJson(
                    registerRequest
                )
            )
            api!!.registerUser(registerRequest).enqueue(object : retrofit2.Callback<RegisterResponse> {

                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    if (response.body()?.result == "success") {
                        finish()
                    } else {
                        showToast(response.body()?.message!!)
                    }
                    hideLoading()

                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    hideLoading()
                }

            })
        } else
            showToast("Check internet connection")
    }
}
