package `in`.motodoc.dap_test

import `in`.medhos.find.doctors.common.PreferenceHelper
import `in`.motodoc.dap_test.base.BaseActivity
import `in`.motodoc.dap_test.fragment.AdminFragment
import `in`.motodoc.dap_test.fragment.UserFragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment

class HomeActivity : BaseActivity() {
    private var shared:PreferenceHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        shared = PreferenceHelper(this)
        if(shared!!.getStringValue(shared!!.role ) == "1"){
            userFragment(AdminFragment())
        }else{
            userFragment(UserFragment())
        }

    }
    private fun userFragment(fragmentClass: Fragment?) {
        val manager = supportFragmentManager
        for (item in 1..manager.backStackEntryCount) {
            manager.popBackStack()
        }
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragmentClass!!)
        transaction.commit()
    }
}
