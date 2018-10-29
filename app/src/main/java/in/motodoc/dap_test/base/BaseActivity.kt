package `in`.motodoc.dap_test.base

import `in`.motodoc.dap_test.R
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import kotlin.properties.Delegates

open class BaseActivity: AppCompatActivity() {
    var mLoadingDialog: Dialog by Delegates.notNull()
    open fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    open fun showLoading(context: Context) {
        try {
            mLoadingDialog = Dialog(context)//new Dialog(context, R.style.ProgressDialog);
            mLoadingDialog.window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
            mLoadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            mLoadingDialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            mLoadingDialog.setContentView(R.layout.dialog_loading)
            mLoadingDialog.setCancelable(false)


            if (!mLoadingDialog.isShowing) {
                mLoadingDialog.show()
            }
        } catch (e: Exception) {
        }

    }

    open fun hideLoading() {
        try {
            if (mLoadingDialog != null && mLoadingDialog.isShowing) {
                mLoadingDialog.dismiss()
            }
        } catch (ex: Exception) {
        }

    }

    open fun networkCheck(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        return isConnected
    }
}