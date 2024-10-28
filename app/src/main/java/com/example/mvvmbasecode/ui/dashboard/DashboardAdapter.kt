package com.example.mvvmbasecode.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmbasecode.database.userdata.UserDataEntity
import com.example.mvvmbasecode.databinding.RowDashboardBinding

class DashboardAdapter(
    var mUserDataEntity: ArrayList<UserDataEntity>
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    class ViewHolder(val binding: RowDashboardBinding) : RecyclerView.ViewHolder(binding.root)

    fun addListOfData(mUserDataEntity: ArrayList<UserDataEntity>) {
        notifyItemRangeRemoved(0, this.mUserDataEntity.size)
        this.mUserDataEntity = mUserDataEntity
        notifyItemRangeChanged(0, this.mUserDataEntity.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowDashboardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.let {
            it.tvTitle.text = mUserDataEntity[position].firstName

//            mContext.setImageUserPlaceHolder(
//                it.civProfile,
//                mUserDataEntity[position].profile_pic
//            )
        }
    }

    override fun getItemCount(): Int {
        return mUserDataEntity.size
    }
}