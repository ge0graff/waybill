package com.example.waybill.data.objects

import java.text.DateFormat
import java.util.*

object DataObject {
    var mouthId = ""

    val data: Date = Calendar.getInstance().time
    val dateFormat: String = DateFormat.getDateInstance(DateFormat.SHORT).format(data)

    private val splitData = dateFormat.split(".")
    val mouths = splitData[1]

}