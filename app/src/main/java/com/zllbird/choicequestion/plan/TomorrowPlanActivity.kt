package com.zllbird.choicequestion.plan

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.zllbird.choicequestion.R
import com.zllbird.choicequestion.choice.model.Action
import com.zllbird.choicequestion.plan.binder.ChoiceStemBinder
import com.zllbird.choicequestion.plan.binder.DayChoiceActionItemBinder
import com.zllbird.choicequestion.plan.binder.DayPlanItemBinder
import kotlinx.android.synthetic.main.activity_create_tomorrow_plan.*
import kotlinx.android.synthetic.main.activity_tomorrow_plan.*
import me.drakeet.multitype.MultiTypeAdapter

class TomorrowPlanActivity: AppCompatActivity(){

    private lateinit var adapter: MultiTypeAdapter
    private lateinit var items: MutableList<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tomorrow_plan)
        initAdapter()
    }

    private fun initAdapter() {
        rv_tomorrow_action.layoutManager = LinearLayoutManager(this)
        items = mutableListOf()
        adapter = MultiTypeAdapter(items)
        adapter.register(Action::class.java, DayPlanItemBinder())
        adapter.register(ChoiceStem::class.java, ChoiceStemBinder())
        rv_tomorrow_action.adapter = adapter
//        rv_tomorrow_action.itemAnimator = SlideInLeftAnimator().apply {
//            removeDuration = 2000
//        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}