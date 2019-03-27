package com.zllbird.choicequestion.choice

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import com.dbflow5.query.list
import com.dbflow5.query.result
import com.dbflow5.query.select
import com.dbflow5.structure.save
import com.zllbird.choicequestion.R
import com.zllbird.choicequestion.choice.model.Action
import com.zllbird.choicequestion.choice.model.Topic
import com.zllbird.choicequestion.choice.model.Topic_Table.id
import io.reactivex.Flowable

import kotlinx.android.synthetic.main.activity_create_action.*
import org.jetbrains.anko.*
class CreateActionActivity: AppCompatActivity() {

    companion object {
        const val CHOICE_TOPIC_REQUEST = 1001
        const val CHOICE_TOPIC_ID = "TopicID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_action)
        btn_choice_topic.setOnClickListener {
            Snackbar.make(it, "Click btn to choice topic", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            startActivityForResult<ChoiceTopicActivity>(CHOICE_TOPIC_REQUEST)
        }
        btn_create_confirm.setOnClickListener {
            submitAction()
        }

        val list = resources.getStringArray(R.array.score)
        val adapter = ArrayAdapter<String>(this,R.layout.spinner_score_item,list)
        sp_create_action_score.adapter = adapter

    }

    private fun submitAction() {
        Snackbar.make(contentView!!, "Click btn to save ", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
        save()
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) return

        when(requestCode){
            CHOICE_TOPIC_REQUEST -> fillTopic(data)
        }
    }

    private fun fillTopic(data: Intent?) {
        if (data != null){
            val topicID =  data.getLongExtra(CHOICE_TOPIC_ID,0)
            val topic = (select from Topic::class.java where (id eq topicID)).result
            btn_choice_topic.text = topic?.title
        }
    }

    private fun testshowactions() {
        (select from Action::class).list.forEach {
            Log.i("TAG",it.title)
        }
    }

    private fun testSaveTopic(){
        Flowable.range(0,10)
            .subscribe {
                Topic().apply {
                    title = "类型编号 $it "
                    superID = 0
                }.save()
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