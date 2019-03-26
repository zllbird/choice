package com.zllbird.choicequestion.choice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.dbflow5.query.list
import com.dbflow5.query.select
import com.zllbird.choicequestion.R
import com.zllbird.choicequestion.choice.model.Action
import com.zllbird.choicequestion.choice.model.Topic
import kotlinx.android.synthetic.main.activity_choice_topic.*
import org.jetbrains.anko.UI
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync

class ChoiceTopicActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_topic)
        loadData()
    }

    fun loadData(){
//        select.from(Action::class)
        doAsync {
            var list = (select from Topic::class).list
            UI {
//                adapter
                list.forEach { Log.i("TAG",it.title) }
            }
        }

    }
}