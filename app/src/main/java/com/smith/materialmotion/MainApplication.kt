package com.smith.materialmotion
import android.app.Application
import android.content.Context
import android.view.WindowManager




class MainApplication : Application(){
    init{
        print("hello")

    }
    override fun onCreate() {
        super.onCreate()

    }
    companion object {
        init {
            print("hello")
        }
    }
}