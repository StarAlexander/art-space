package com.example.myapplication.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
class PictureViewModel:ViewModel() {
    private var _pic= MutableStateFlow(0)
    var pic: StateFlow<Int> =_pic.asStateFlow()

    fun changePositive(){
        _pic.update{
            if (it+1>listOfPics.list.size-1) 0 else it+1
        }
    }
    fun changeNegative(){
        _pic.update{
            if (it==0) listOfPics.list.size-1 else it-1
        }
    }

}