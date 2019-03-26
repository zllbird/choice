package com.zllbird.choicequestion.choice

import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.dbflow5.query.list
import com.dbflow5.query.select
import com.dbflow5.structure.save
import com.zllbird.choicequestion.R
import com.zllbird.choicequestion.choice.model.Action

import kotlinx.android.synthetic.main.activity_create_action.*
class CreateActionActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_action)
        btn_choice_topic.setOnClickListener {
            Snackbar.make(it, "Click btn to choice topic", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            testshowactions()
        }
        btn_create_confirm.setOnClickListener {
            save()
            Snackbar.make(it, "Click btn to save ", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun testshowactions() {
        (select from Action::class).list.forEach {
            Log.i("TAG",it.title)
        }
    }

    private fun save() {
        Action().apply {
            title = et_create_action_title.text.toString().trim()
            topicID = 0
            score = sp_create_action_score.selectedItemId + 1
            userID = 999
        }.save()
    }

}