package com.zllbird.choicequestion.plan

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.dbflow5.query.OrderBy
import com.dbflow5.query.list
import com.dbflow5.query.select
import com.orhanobut.logger.Logger
import com.zllbird.choicequestion.R
import com.zllbird.choicequestion.choice.model.Action
import com.zllbird.choicequestion.choice.model.Action_Table
import com.zllbird.choicequestion.plan.binder.DayChoiceActionItemBinder
import kotlinx.android.synthetic.main.activity_create_tomorrow_plan.*
import me.drakeet.multitype.MultiTypeAdapter
import kotlin.random.Random

class CreateTomorrowPlanActivity : AppCompatActivity() {

    companion object {
        const val TOPIC_FIT = 1L
    }

    private lateinit var adapter: MultiTypeAdapter
    private lateinit var items: MutableList<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_tomorrow_plan)
        initAdapter()
        loadData(TOPIC_FIT)
        initListener()
    }

    private fun initListener() {
        btn_load_data.setOnClickListener { loadData(TOPIC_FIT) }
    }

    private fun loadData(topicID: Long) {
        val list =
            (select from Action::class where (Action_Table.topicID eq topicID) orderBy OrderBy.fromString("RANDOM()") limit 3).list
        items.addAll(list)
        Logger.i("list count is ${items.count()}")
        adapter.notifyDataSetChanged()
    }

    private fun initAdapter() {
        rv_choice_tomorrow_action.layoutManager = LinearLayoutManager(this)
        items = mutableListOf()
        adapter = MultiTypeAdapter(items)
        adapter.register(Action::class.java, DayChoiceActionItemBinder {
            Logger.i("$it")
        })
        rv_choice_tomorrow_action.adapter = adapter
    }

}