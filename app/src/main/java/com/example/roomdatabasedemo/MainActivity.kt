package com.example.roomdatabasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabasedemo.constans.AppConstants
import com.example.roomdatabasedemo.databinding.ActivityMainBinding
import com.example.roomdatabasedemo.persistance.AppDatabase
import com.example.roomdatabasedemo.persistance.entity.Subscriber
import com.example.roomdatabasedemo.persistance.repository.SubscriberRepository
import com.example.roomdatabasedemo.persistance.sharedPreferences.SubscriberPreferences
import com.example.roomdatabasedemo.persistance.viewmodel.SubscriberViewModel
import com.example.roomdatabasedemo.persistance.viewmodel.SubscriberViewModelFactory
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private var subscriberPrefs: SubscriberPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscriberPrefs = SubscriberPreferences.getInstance(this)
        val dao = AppDatabase.getInstance(application).subscriberDao()

        val repository = SubscriberRepository(dao)

        val factory = SubscriberViewModelFactory(repository)

        subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)

//        subscriberViewModel = viewModelProviders.of(this).get(SubscriberViewModel::class.java)
//        subscriberViewModel = ViewModelProvider(this).get(SubscriberViewModel::class.java)


        binding.saveBtn.setOnClickListener {
            val name = binding.nameTIET.text.toString()
            val email = binding.emailTIET.text.toString()


            val id = if(subscriberPrefs?.getBoolean(SubscriberPreferences.Key.IS_UPDATE,false) == true) {
                subscriberPrefs?.getInt(SubscriberPreferences.Key.ID)!!
            } else {
                0
            }
            val subscriber = Subscriber(id, name, email)


            if (subscriberPrefs?.getBoolean(SubscriberPreferences.Key.IS_UPDATE, false) == true) {
                subscriberViewModel.update(subscriber)
                Toast.makeText(this, "${subscriberPrefs?.getBoolean(SubscriberPreferences.Key.IS_UPDATE, false)}", Toast.LENGTH_LONG ).show()
                binding.saveBtn.text = "SAVE"
                subscriberPrefs!!.put(SubscriberPreferences.Key.IS_UPDATE, false)
            } else {
                subscriberViewModel.insert(subscriber)
            }

            binding.nameTIET.text = null
            binding.emailTIET.text = null
        }

        binding.deleteAllBtn.setOnClickListener {
            subscriberViewModel.deleteAll()
        }


        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.subscribersRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val divider = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            addItemDecoration(divider)
        }
        displaySubscribers()
    }

    private fun displaySubscribers() {

        subscriberViewModel.subscribers.observe(
            this, Observer {
                binding.subscribersRecyclerView.adapter = MyRecyclerViewAdapter(it, {selectedItem:Subscriber->listItemClicked(selectedItem)})
            }
        )
    }

    private fun listItemClicked(subscriber: Subscriber) {
        val updateOrDeleteStatus = subscriberPrefs?.getString(SubscriberPreferences.Key.UPDATE_OR_DELETE_STATUS)
        when(updateOrDeleteStatus) {
            AppConstants.DELETE -> {
                subscriberViewModel.delete(subscriber)
            }
            AppConstants.UPDATE -> {
                subscriberPrefs?.put(SubscriberPreferences.Key.IS_UPDATE,true)
                binding.saveBtn.text = "UPDATE"
                SubscriberPreferences.getInstance(this)
                    ?.put(SubscriberPreferences.Key.ID, subscriber.id)
                binding.nameTIET.setText(subscriber.name)
                binding.emailTIET.setText(subscriber.email)

                Toast.makeText(this, "UPDATE CLICKED", Toast.LENGTH_LONG).show()
            }
        }
//        Toast.makeText(this, "$updateOrDeleteStatus", Toast.LENGTH_LONG).show()

//        subscriberViewModel.delete(subscriber)
    }




}