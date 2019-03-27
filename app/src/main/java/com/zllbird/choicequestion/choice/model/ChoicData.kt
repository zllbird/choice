package com.zllbird.choicequestion.choice.model

import com.dbflow5.annotation.Column
import com.dbflow5.annotation.PrimaryKey
import com.dbflow5.annotation.Table
import com.zllbird.choicequestion.AppDatabase

@Table(database = AppDatabase::class) data class Action(
    @PrimaryKey(autoincrement = true) var id: Long = 0,
    @Column var title: String? = null,
    @Column var topicID: Long = 0,
    @Column var score: Long = 0,
    @Column var userID: Long = 0
)

@Table(database = AppDatabase::class) data class Topic(
    @PrimaryKey(autoincrement = true) var id: Long = 0,
    @Column var title: String? = null,
    @Column var superID: Long = 0
)

@Table(database = AppDatabase::class) data class SuperTopic(
    @PrimaryKey(autoincrement = true) var id: Long = 0,
    @Column var title: String? = null
)