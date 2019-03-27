package com.zllbird.choicequestion.plan.binder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zllbird.choicequestion.R
import com.zllbird.choicequestion.choice.model.Action
import kotlinx.android.synthetic.main.item_day_plan.view.*
import me.drakeet.multitype.ItemViewBinder

class DayPlanItemBinder : ItemViewBinder<Action,DayPlanItemBinder.ViewHolder>(){
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_day_plan,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Action) {
        holder.onBindView(item)
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        fun onBindView(item: Action){
            itemView.tv_plan_action.text = "${item.title} 分数为： ${item.score}"
        }
    }
}