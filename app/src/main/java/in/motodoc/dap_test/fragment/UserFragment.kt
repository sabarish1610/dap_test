package `in`.motodoc.dap_test.fragment


import `in`.medhos.find.doctors.common.PreferenceHelper
import `in`.medhos.fordoctors.adapter.pharmalab.UserListAdapter
import `in`.motodoc.dap_test.LoginActivity
import `in`.motodoc.dap_test.R
import `in`.motodoc.dap_test.base.BaseFragment
import `in`.motodoc.dap_test.model.*
import `in`.motodoc.dap_test.retrofitService.ApiClient
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_user.view.*
import retrofit2.Call
import retrofit2.Response

class UserFragment : BaseFragment() {
    private var shared: PreferenceHelper? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shared = PreferenceHelper(activity!!)
        initView(view)
        loadData()

    }

    private fun loadData() {
        if (networkCheck(activity!!)) {
            showLoading(activity!!)
        val api = ApiClient().mServiceAPI()
        val userDescription = UserDescription(shared!!.getStringValue(shared!!.emailId)!!)
        val loginRequest = userDescrtptionRequest("getUserDetails", userDescription)

        Log.d(
            "mmm request",
            Gson().toJson(
                loginRequest
            )
        )
        api!!.getDescriptionList(loginRequest).enqueue(object : retrofit2.Callback<userDescrtptionResponse> {

            override fun onResponse(call: Call<userDescrtptionResponse>, response: Response<userDescrtptionResponse>) {
                Log.d(
                    "mmm response", Gson().toJson(
                        response.body()
                    )
                )
                if (response.body()?.result == "success") {
                    if (response.body()!!.descriptionList != null && response.body()!!.descriptionList.isNotEmpty())
                        view?.user_list?.adapter = UserListAdapter(activity!!, response.body()!!.descriptionList)

                } else {
                    showToast(response.body()?.message!!)
                }
                hideLoading()

            }

            override fun onFailure(call: Call<userDescrtptionResponse>, t: Throwable) {
                hideLoading()
            }

        })
        }else
            showToast("Check internet connection")

    }

    private fun initView(view: View) {
        view.user_list.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        view.user_sing_out.setOnClickListener {
            shared?.clearShared()
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }


    }


}
