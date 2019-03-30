package com.zllbird.choicequestion.plan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import com.dbflow5.annotation.PrimaryKey
import com.dbflow5.query.OrderBy
import com.dbflow5.query.list
import com.dbflow5.query.result
import com.dbflow5.query.select
import com.orhanobut.logger.Logger
import com.zllbird.choicequestion.R
import com.zllbird.choicequestion.choice.model.Action
import com.zllbird.choicequestion.choice.model.Action_Table
import com.zllbird.choicequestion.choice.model.Topic
import com.zllbird.choicequestion.choice.model.Topic_Table
import com.zllbird.choicequestion.plan.binder.ChoiceStemBinder
import com.zllbird.choicequestion.plan.binder.DayChoiceActionItemBinder
import kotlinx.android.synthetic.main.activity_create_tomorrow_plan.*
import me.drakeet.multitype.MultiTypeAdapter
import kotlin.random.Random

class CreateTomorrowPlanActivity : AppCompatActivity() {

    companion object {
        const val TOPIC_FIT = 0L
    }

    private lateinit var adapter: MultiTypeAdapter
    private lateinit var items: MutableList<Any>

    private lateinit var selectedActions: MutableList<Action>

    private val maxCount = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_tomorrow_plan)
        initAdapter()
        loadData(TOPIC_FIT)
        initListener()
    }

    private fun initListener() {
        tv_selected_actions.setOnClickListener {
            val options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this,
//                    tv_selected_actions to tv_selected_actions.transitionName,
                        Pair(tv_selected_actions,tv_selected_actions.transitionName)
                    )
            val intent = Intent(this,TomorrowPlanActivity::class.java)
            startActivity(intent,options.toBundle())
        }
    }

    private fun nextAction(){
        items.clear()
        loadData(Random(10L).nextLong(0L,15L))
    }

    private fun loadData(topicID: Long) {
        val topic = (select from Topic::class where (Topic_Table.id eq topicID)).result
        if (topic != null){
            items.add(ChoiceStem(topic.title!!,selectedActions.size+1))
        }
        val list =
            (select from Action::class where (Action_Table.topicID eq topicID) orderBy OrderBy.fromString("RANDOM()") limit 3).list
        items.addAll(list)
        Logger.i("list count is ${items.count()}")
        adapter.notifyDataSetChanged()
    }

    private fun slideRightIn() {
        val slide = Slide(Gravity.RIGHT)
        TransitionManager.beginDelayedTransition(findViewById(android.R.id.content),slide)
        rv_choice_tomorrow_action.visibility = View.VISIBLE
    }

    private fun addSelectedData(action:Action){
        selectedActions.add(action)
        tv_selected_actions.text = "明日已安排 ${selectedActions.count()} 事项"
    }

    private fun initAdapter() {
        rv_choice_tomorrow_action.layoutManager = LinearLayoutManager(this)
        items = mutableListOf()
        selectedActions = mutableListOf()
        adapter = MultiTypeAdapter(items)
        adapter.register(Action::class.java, DayChoiceActionItemBinder { action, i ->
            if (selectedActions.count() < maxCount) addSelectedData(action)
            else showDialogForPlanList()
        })
        adapter.register(ChoiceStem::class.java, ChoiceStemBinder())
        rv_choice_tomorrow_action.adapter = adapter
//        rv_choice_tomorrow_action.itemAnimator = SlideInLeftAnimator().apply {
//            removeDuration = 2000
//        }
    }

    private fun showDialogForPlanList() {
        AlertDialog.Builder(this)
            .setTitle("明日计划已生成")
            .setPositiveButton("确定") { _, _ ->
                saveTomorrowPlan()
                setResult(Activity.RESULT_OK)
                finish()
            }
            .create()
    }

    private fun saveTomorrowPlan() {

    }

    private val sliderListner = SlideLeftOutListner { nextAction() }

    private fun slideLeftOut() {
        val slide = Slide(Gravity.LEFT).addListener(sliderListner)
        TransitionManager.beginDelayedTransition(findViewById(android.R.id.content),slide)
        rv_choice_tomorrow_action.visibility = View.GONE
    }

    class SlideLeftOutListner(var onTransitionEnd:()->Unit) : Transition.TransitionListener{
        override fun onTransitionEnd(transition: Transition?) {
            Logger.i("onTransitionEnd")
            onTransitionEnd();
        }

        override fun onTransitionResume(transition: Transition?) {
            Logger.i("onTransitionResume")
        }

        override fun onTransitionPause(transition: Transition?) {
            Logger.i("onTransitionPause")
        }

        override fun onTransitionCancel(transition: Transition?) {
            Logger.i("onTransitionCancel")
        }

        override fun onTransitionStart(transition: Transition?) {
            Logger.i("onTransitionStart")
        }
    }

}