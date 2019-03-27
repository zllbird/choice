package com.zllbird.choicequestion.choice.binder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orhanobut.logger.Logger
import com.zllbird.choicequestion.R
import com.zllbird.choicequestion.choice.model.Topic
import me.drakeet.multitype.ItemViewBinder

import kotlinx.android.synthetic.main.item_choice_topic.view.*

class ChoiceTopicItemBinder(private val itemOnClick:(Topic)->Unit) : ItemViewBinder<Topic, ChoiceTopicItemBinder.ViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_choice_topic, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Topic) {
        holder.onBindView(item,itemOnClick)
        Logger.i(item.toString())
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun onBindView(item: Topic,itemOnClick:(Topic)->Unit){
            itemView.tv_choice_topic_item.text = item.title
            itemView.setOnClickListener { itemOnClick(item) }
        }
    }
}