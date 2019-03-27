package com.zllbird.choicequestion.choice

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.dbflow5.query.list
import com.dbflow5.query.select
import com.orhanobut.logger.Logger
import com.zllbird.choicequestion.R
import com.zllbird.choicequestion.choice.model.Topic
import com.zllbird.choicequestion.choice.binder.ChoiceTopicItemBinder
import kotlinx.android.synthetic.main.activity_choice_topic.*
import me.drakeet.multitype.MultiTypeAdapter

class ChoiceTopicActivity : AppCompatActivity() {

    lateinit var adapter: MultiTypeAdapter
    private lateinit var items: MutableList<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_topic)
        initAdapter()
        loadData()
    }

    private fun initAdapter() {
        items = mutableListOf()
        adapter = MultiTypeAdapter(items)
        adapter.register(Topic::class.java, ChoiceTopicItemBinder { resultForChoiceTopic(it) })
        rv_topics.adapter = adapter
        rv_topics.layoutManager = LinearLayoutManager(this)
    }

    private fun resultForChoiceTopic(it: Topic) {
        val intent = Intent()
        intent.putExtra(CreateActionActivity.CHOICE_TOPIC_ID, it.id)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    private fun loadData() {
        val list = (select from Topic::class).list
        Logger.i("list count is ${list.count()}")
        items.addAll(list)
        Logger.i("items count is ${items.count()}")
    }
}