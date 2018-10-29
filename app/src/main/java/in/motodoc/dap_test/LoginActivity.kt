package `in`.motodoc.dap_test

import `in`.medhos.find.doctors.common.PreferenceHelper
import `in`.motodoc.dap_test.base.BaseActivity
import `in`.motodoc.dap_test.model.*
import `in`.motodoc.dap_test.retrofitService.ApiClient
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Response

class LoginActivity : BaseActivity() {
    private var shared: PreferenceHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shared = PreferenceHelper(this)

        if (shared!!.getBooleanValue(shared!!.isLoggedIN)!!)
        {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        sign_in.setOnClickListener {
            when{
                email_id.text.toString().isEmpty() -> showToast("Enter Email id")
                password.text.toString().isEmpty() -> showToast("Enter password")
                else -> login()
            }

        }
        register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun login() {
        if (networkCheck(this)) {
            showLoading(this)
            val api = ApiClient().mServiceAPI()
            val login = LoginDetails(email_id.text.toString(), password.text.toString())
            val loginRequest = LoginRequest("loginUser", login)

            Log.d(
                "mmm request",
                Gson().toJson(
                    loginRequest
                )
            )
            api!!.loginResponse(loginRequest).enqueue(object : retrofit2.Callback<LoginResponse> {

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                      Log.d("mmm response", Gson().toJson(response.body()))
                    if (response.body()?.result == "success") {
                        shared?.setStringValue(shared!!.emailId, response.body()!!.loginDetails.email)
                        shared?.setStringValue(shared!!.role, response.body()!!.loginDetails.role)
                        shared?.setBooleanValue(shared!!.isLoggedIN, true)
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()
                    } else {
                        showToast(response.body()!!.message)

                    }
                    hideLoading()

                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    hideLoading()

                }

            })
        }else
            showToast("Check internet connection")
    }

    }

