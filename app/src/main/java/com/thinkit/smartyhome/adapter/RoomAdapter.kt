package com.thinkit.smartyhome.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.thinkit.smartyhome.R
import com.thinkit.smartyhome.model.Room

import kotlinx.android.synthetic.main.room_item.view.*

class RoomAdapter(private val rooms: ArrayList<Room>) : RecyclerView.Adapter<RoomAdapter.RoomHolder>()  {


    override fun onBindViewHolder(holder: RoomHolder, position: Int) {
        val itemRoom = rooms[position]
        holder.bindRoom(itemRoom)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolder {
        val inflatedView = parent.inflate(R.layout.room_item, false)
        return RoomHolder(inflatedView)

    }
    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
    override fun getItemCount() = rooms.size

    class RoomHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var room: Room? = null


        init {
            v.setOnClickListener(this)
        }


        override fun onClick(v: View) {

        }


        fun bindRoom(room: Room) {
            this.room = room
            view.roomImage.setImageResource(view.context.resources.getIdentifier(room.image,"drawable",view.context.getPackageName()))
            view.roomName.text = room.name
            if(room.numberOfDevices>1){
                view.deviceNumber.text = "${room.numberOfDevices} Devices"
            }else{
                view.deviceNumber.text = "${room.numberOfDevices} Device"
            }

        }
    }
}