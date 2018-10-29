package `in`.medhos.fordoctors.adapter.pharmalab

import `in`.motodoc.dap_test.R
import `in`.motodoc.dap_test.model.DescriptionList
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list_pharma.view.*

class UserListAdapter(private val context: Context?, private val descriptionList: List<DescriptionList>?) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.ViewHolder {

        var view: View? = LayoutInflater.from(parent!!.context).inflate(R.layout.item_list_pharma, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemCount(): Int = descriptionList!!.size

    override fun onBindViewHolder(holder: UserListAdapter.ViewHolder, position: Int) {
        holder!!.bind(descriptionList!![position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



        fun bind(labData: DescriptionList) {
            itemView.description.text = labData.description
        }
    }

}
