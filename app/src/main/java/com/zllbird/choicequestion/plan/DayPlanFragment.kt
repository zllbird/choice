package com.zllbird.choicequestion.plan

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dbflow5.query.list
import com.dbflow5.query.select
import com.zllbird.choicequestion.R
import com.zllbird.choicequestion.choice.model.Action
import com.zllbird.choicequestion.plan.binder.DayPlanItemBinder
import kotlinx.android.synthetic.main.fragment_day_plan.*
import me.drakeet.multitype.MultiTypeAdapter

class DayPlanFragment : Fragment() {

    lateinit var adapter: MultiTypeAdapter
    private lateinit var items: MutableList<Any>

    companion object {
        fun newInstance(): DayPlanFragment {
            return DayPlanFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_day_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListAdapter()
        loadData()
    }

    private fun loadData() {
        val list = (select from Action::class).list
        items.addAll(list)
    }

    private fun initListAdapter() {
        items = mutableListOf()
        adapter = MultiTypeAdapter(items)
        adapter.register(Action::class.java, DayPlanItemBinder())
        rv_plan_action.adapter = adapter
        rv_plan_action.layoutManager = LinearLayoutManager(activity)
    }

}