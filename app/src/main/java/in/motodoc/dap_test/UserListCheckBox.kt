package `in`.motodoc.dap_test

import `in`.medhos.find.doctors.common.PreferenceHelper
import `in`.motodoc.dap_test.adapter.CheckBoxListAdapter
import `in`.motodoc.dap_test.base.BaseActivity
import `in`.motodoc.dap_test.model.*
import `in`.motodoc.dap_test.retrofitService.ApiClient
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_user_list_check_box.*
import retrofit2.Call
import retrofit2.Response

class UserListCheckBox : BaseActivity() {
    private var shared: PreferenceHelper? = null
    private val count = 0
    private var description = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list_check_box)
        user_list_check_box.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        description = intent.getStringExtra("Description")
        shared = PreferenceHelper(this)
        loadData()
        submit.setOnClickListener {
            //insertDescription()
            insertDescription()
        }
    }


    private fun insertDescription() {
        if (networkCheck(this)) {
            showLoading(this)
            val api = ApiClient().mServiceAPI()
            val userDescription = InsertDescription(description, checkBoxList)
            val loginRequest = InsertDescriptionRequest("insertDescription", userDescription)

            Log.d(
                "mmm request",
                Gson().toJson(
                    loginRequest
                )
            )
            api!!.insertDescription(loginRequest).enqueue(object : retrofit2.Callback<InsertDescriptionResponse> {

                override fun onResponse(
                    call: Call<InsertDescriptionResponse>,
                    response: Response<InsertDescriptionResponse>
                ) {
                    Log.d(
                        "mmm response", Gson().toJson(
                            response.body()
                        )
                    )
                    if (response.body()?.result == "success")
                        finish()
                    else
                        showToast("Something went wrong")

                    hideLoading()

                }

                override fun onFailure(call: Call<InsertDescriptionResponse>, t: Throwable) {
                    hideLoading()
                }

            })
        } else
            showToast("Check internet connection")

    }

    private fun loadData() {
        if (networkCheck(this)) {
            showLoading(this)
            val api = ApiClient().mServiceAPI()
            val userDescription = UserListForAssign(shared!!.getStringValue(shared!!.emailId)!!)
            val loginRequest = UserListForAssignRequest("getUserListAssign", userDescription)

            Log.d(
                "mmm request",
                Gson().toJson(
                    loginRequest
                )
            )
            api!!.getDescriptionListBox(loginRequest).enqueue(object : retrofit2.Callback<UserListForAssignResponse> {

                override fun onResponse(
                    call: Call<UserListForAssignResponse>,
                    response: Response<UserListForAssignResponse>
                ) {
                    Log.d(
                        "mmm response", Gson().toJson(
                            response.body()
                        )
                    )
                    if (response.body()?.result == "success") {
                        if (response.body()!!.listForAssign != null && response.body()!!.listForAssign.isNotEmpty()) {
                            checkBoxList = response.body()!!.listForAssign
                            user_list_check_box?.adapter =
                                    CheckBoxListAdapter(this@UserListCheckBox, response.body()!!.listForAssign)

                        }
                    } else {
                        showToast(response.body()?.message!!)
                    }
                    hideLoading()

                }

                override fun onFailure(call: Call<UserListForAssignResponse>, t: Throwable) {
                    hideLoading()
                }

            })
        } else
            showToast("Check internet connection")

    }

    companion object {
        var checkBoxList: MutableList<ListForAssign> = ArrayList()
    }
}
