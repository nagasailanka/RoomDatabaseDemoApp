package com.example.roomdatabasedemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasedemo.constans.AppConstants
import com.example.roomdatabasedemo.databinding.ListTemRowBinding
import com.example.roomdatabasedemo.persistance.entity.Subscriber
import com.example.roomdatabasedemo.persistance.sharedPreferences.SubscriberPreferences

class MyRecyclerViewAdapter(private val subscriberList: List<Subscriber>, private val clickListener: (Subscriber)->Unit): RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding = ListTemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(subscriberList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return subscriberList.size
    }

    inner class MyViewHolder(private val binding: ListTemRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit){

            binding.apply {
                listNameTV.text = subscriber.name
                listEmailTV.text = subscriber.email
            }

//            binding.listItemLayout.setOnClickListener {
//                clickListener(subscriber)
//            }

            binding.deleteIcon.setOnClickListener {
                SubscriberPreferences.getInstance()?.put(SubscriberPreferences.Key.UPDATE_OR_DELETE_STATUS, AppConstants.DELETE)
                clickListener(subscriber)
            }

            binding.updateIcon.setOnClickListener {
                SubscriberPreferences.getInstance()?.put(SubscriberPreferences.Key.UPDATE_OR_DELETE_STATUS, AppConstants.UPDATE)
                clickListener(subscriber)
            }
        }
    }
}