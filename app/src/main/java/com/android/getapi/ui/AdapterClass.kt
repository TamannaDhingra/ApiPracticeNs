package com.android.getapi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.getapi.databinding.InflaterAllDetailsBinding
import com.android.getapi.models.GetAllDetailsModel

class AdapterClass(val list1:List<GetAllDetailsModel>):RecyclerView.Adapter<AdapterClass.ViewHolderClss>() {

    inner class ViewHolderClss(var binding: InflaterAllDetailsBinding)
        :RecyclerView.ViewHolder(binding.root){
            fun AllDetail(data:GetAllDetailsModel){
                binding.details = data
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterClass.ViewHolderClss {
        val binding= InflaterAllDetailsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ViewHolderClss(binding)
    }

    override fun onBindViewHolder(holder: AdapterClass.ViewHolderClss, position: Int) {
        holder.AllDetail(list1.get(position))
    }

    override fun getItemCount(): Int {
        return list1.size
    }

}