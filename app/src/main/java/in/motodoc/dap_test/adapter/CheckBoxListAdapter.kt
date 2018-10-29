package `in`.motodoc.dap_test.adapter

import `in`.motodoc.dap_test.R
import `in`.motodoc.dap_test.UserListCheckBox
import `in`.motodoc.dap_test.model.ListForAssign
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list_check_boc.view.*

class CheckBoxListAdapter(val context: Context, val checkListItem:List<ListForAssign>) : RecyclerView.Adapter<CheckBoxListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View? = LayoutInflater.from(parent!!.context).inflate(R.layout.item_list_check_boc, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemCount(): Int = checkListItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.check_box.setOnCheckedChangeListener { compoundButton, b ->
            if(b){
                UserListCheckBox.checkBoxList[position] =
                        ListForAssign(checkListItem[position].name, checkListItem[position].email, 1)
            }else{
                UserListCheckBox.checkBoxList[position] =
                        ListForAssign(checkListItem[position].name, checkListItem[position].email, 0)
            }
        }
        holder!!.bind(checkListItem!![position])

    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



        fun bind(labData: ListForAssign) {
            itemView.user.text = labData.name

        }
    }
}