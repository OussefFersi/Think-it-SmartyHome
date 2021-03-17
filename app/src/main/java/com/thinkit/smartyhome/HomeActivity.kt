package com.thinkit.smartyhome

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thinkit.smartyhome.adapter.RoomAdapter
import com.thinkit.smartyhome.model.Room
import com.thinkit.smartyhome.model.RoomViewModel
import com.thinkit.smartyhome.model.RoomViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class HomeActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var sharedPref: SharedPreferences? = null
    private lateinit var viewModel: RoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sharedPref = getSharedPreferences(MainActivity.SHARED_PREF_KEY,Context.MODE_PRIVATE)
        val currentUserName =  sharedPref!!.getString(MainActivity.USER_NAME_KEY,"Unkown")
        homeWelcomeMessage.text = "Welcome, $currentUserName!"
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)

        currentDateText.text =  formatted

        linearLayoutManager = LinearLayoutManager(this)
        mHomeRecycleView.layoutManager = linearLayoutManager
        val factory = RoomViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(RoomViewModel::class.java)

        observeData()
        addData()

    }

    fun observeData(){
        viewModel.lst.observe(this, Observer{
            mHomeRecycleView.adapter= RoomAdapter( it)
        })
    }


    fun addData(){


            var livingroom = Room("Living Room",4,"livingroom")
            viewModel.add(livingroom)
            var mediaRoom = Room("Media Room",6,"mediaroom")
            viewModel.add(mediaRoom)
        var bathroom = Room("Bathroom",1,"bathroom")
        viewModel.add(bathroom)
        var bedRoom = Room("Bedroom",3,"bedroom")
        viewModel.add(bedRoom)
             mHomeRecycleView.adapter?.notifyDataSetChanged()


    }

    override fun onBackPressed() {

    }
}