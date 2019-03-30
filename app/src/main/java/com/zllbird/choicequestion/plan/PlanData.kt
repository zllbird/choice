package com.zllbird.choicequestion.plan

import android.icu.util.Calendar
import com.dbflow5.annotation.Column
import com.dbflow5.annotation.PrimaryKey
import com.dbflow5.annotation.Table
import com.dbflow5.annotation.Unique
import com.dbflow5.contentprovider.annotation.ContentUri
import com.zllbird.choicequestion.AppDatabase
import com.zllbird.choicequestion.choice.model.Action

data class ChoiceStem(
    var topicTitle: String,
    var position: Int
)

@Table(database = AppDatabase::class) data class ActionRecord(
    @PrimaryKey(autoincrement = true) var id: Long = 0,
    @Column var actionId: Long,
    @Column var planTime: Calendar,
    @Column var topicId:Long,
    @Column var userId : Long
)