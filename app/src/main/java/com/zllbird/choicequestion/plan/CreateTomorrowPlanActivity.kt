package com.zllbird.choicequestion.plan

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.transition.ChangeBounds
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import com.dbflow5.query.OrderBy
import com.dbflow5.query.list
import com.dbflow5.query.select
import com.orhanobut.logger.Logger
import com.zllbird.choicequestion.R
import com.zllbird.choicequestion.choice.model.Action
import com.zllbird.choicequestion.choice.model.Action_Table
import com.zllbird.choicequestion.plan.binder.ChoiceStemBinder
import com.zllbird.choicequestion.plan.binder.DayChoiceActionItemBinder
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator
import kotlinx.android.synthetic.main.activity_create_tomorrow_plan.*
import me.drakeet.multitype.MultiTypeAdapter
import org.jetbrains.anko.contentView
import kotlin.random.Random
import org.jetbrains.anko.*

class CreateTomorrowPlanActivity : AppCompatActivity() {

    companion object {
        const val TOPIC_FIT = 0L
    }

    private lateinit var adapter: MultiTypeAdapter
    private lateinit var items: MutableList<Any>

    var change = false
    private lateinit var selectedActions: MutableList<Action>

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
//        loadData()
    }

    private fun loadData(topicID: Long) {
        items.add(ChoiceStem("哈哈",7))
        val list =
            (select from Action::class where (Action_Table.topicID eq topicID) orderBy OrderBy.fromString("RANDOM()") limit 3).list
        items.addAll(list)
        Logger.i("list count is ${items.count()}")
        adapter.notifyDataSetChanged()
    }

    private fun addSelectedData(action:Action){
        selectedActions.add(action)
        tv_selected_actions.text = "明日已安排${selectedActions.count()}事项，点击查看"
    }

    private fun initAdapter() {
        rv_choice_tomorrow_action.layoutManager = LinearLayoutManager(this)
        items = mutableListOf()
        selectedActions = mutableListOf()
        adapter = MultiTypeAdapter(items)
        adapter.register(Action::class.java, DayChoiceActionItemBinder { action, i ->
//            items.remove(action)
//            adapter.notifyItemRemoved(i)
            addSelectedData(action)
        })
        adapter.register(ChoiceStem::class.java, ChoiceStemBinder())
        rv_choice_tomorrow_action.adapter = adapter
//        rv_choice_tomorrow_action.itemAnimator = SlideInLeftAnimator().apply {
//            removeDuration = 2000
//        }
    }

    private fun slideLeftOut() {
//        val slide = Slide(Gravity.LEFT)
//        TransitionManager.beginDelayedTransition(findViewById(android.R.id.content),slide)
//        if (!change){
//            rv_choice_tomorrow_action.visibility = View.GONE
//        }else{
//            rv_choice_tomorrow_action.visibility = View.VISIBLE
//        }
//        change = !change
    }

}