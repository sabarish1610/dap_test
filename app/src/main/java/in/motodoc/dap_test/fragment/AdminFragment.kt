package `in`.motodoc.dap_test.fragment


import `in`.medhos.find.doctors.common.PreferenceHelper
import `in`.motodoc.dap_test.LoginActivity
import `in`.motodoc.dap_test.R
import `in`.motodoc.dap_test.UserListCheckBox
import `in`.motodoc.dap_test.base.BaseFragment
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_admin.view.*

class AdminFragment : BaseFragment() {
    private var shared: PreferenceHelper? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shared = PreferenceHelper(activity!!)
        initView(view)
    }

    private fun initView(view: View) {

        view.assign.setOnClickListener {
            val description = view.description.text.toString()
            if (description.isNotEmpty())
            startActivity(Intent(activity, UserListCheckBox::class.java).putExtra("Description", description))
        }

        view.admin_sing_out.setOnClickListener {
            shared?.clearShared()
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }

    }


}
