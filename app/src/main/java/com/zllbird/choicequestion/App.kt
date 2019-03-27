package com.zllbird.choicequestion

import android.app.Application
import com.dbflow5.config.FlowManager
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FlowManager.init(this)
        initLogger()
    }

    fun initLogger(){
        Logger.addLogAdapter(AndroidLogAdapter())
    }

}