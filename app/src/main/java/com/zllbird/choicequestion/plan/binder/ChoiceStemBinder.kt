package com.zllbird.choicequestion.plan.binder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zllbird.choicequestion.R
import com.zllbird.choicequestion.plan.ChoiceStem
import kotlinx.android.synthetic.main.item_choice_stem.view.*
import me.drakeet.multitype.ItemViewBinder

class ChoiceStemBinder : ItemViewBinder<ChoiceStem,ChoiceStemBinder.ViewHolder>(){
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_choice_stem,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: ChoiceStem) {
        holder.onBindView(item)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun onBindView(item: ChoiceStem) {
            itemView.tv_choice_stem.text = "${item.position}. 请选择 #${item.topicTitle}# 项:"
        }

    }

}