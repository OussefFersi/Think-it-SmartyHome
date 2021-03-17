package com.thinkit.smartyhome.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RoomViewModelFactory (): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RoomViewModel::class.java)){
            return RoomViewModel() as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }

}
