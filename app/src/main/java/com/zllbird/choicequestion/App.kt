package com.zllbird.choicequestion

import android.app.Application
import com.dbflow5.config.FlowManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FlowManager.init(this)
    }

}