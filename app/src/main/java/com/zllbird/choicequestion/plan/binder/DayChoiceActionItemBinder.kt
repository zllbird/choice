package com.zllbird.choicequestion.plan.binder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zllbird.choicequestion.R
import com.zllbird.choicequestion.choice.model.Action
import kotlinx.android.synthetic.main.item_choice_action.view.*
import me.drakeet.multitype.ItemViewBinder

class DayChoiceActionItemBinder(val onActionClick: (Action)->Unit) : ItemViewBinder<Action,DayChoiceActionItemBinder.ViewHolder>(){
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_choice_action,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Action) {
        holder.onBindView(item,getPosition(holder),onActionClick)
        holder.itemView.setOnClickListener { onActionClick(item) }
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        fun onBindView(
            item: Action,
            position: Int,
            onActionClick: (Action) -> Unit
        ){
            itemView.tv_choice_action.text = "${changIndex(position)}. ${item.title} (${item.score})"
//            itemView.setOnClickListener { onActionClick(item) }
        }

        fun changIndex(position:Int) = when(position){
            0 -> "A"
            1 -> "B"
            2 -> "C"
            else -> "D"
        }

    }
}