package com.example.swap

import java.text.SimpleDateFormat
import java.util.*

object  DateUtil{
    val nowDateTime: String
    get() {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")  //2020-08-17 19-13-00
        return  sdf.format(Date())
    }

    val nowDate: String
    get(){
        val sdf = SimpleDateFormat("yyyy-MM-dd")  //2020-08-17  只有日期
        return  sdf.format(Date())
    }

    val nowTime: String
    get() {
        val sdf = SimpleDateFormat("HH:mm:ss")  // 19-13-00 只有時間
        return  sdf.format(Date())
    }

    val nowTimeDetail: String
    get(){
        val sdf = SimpleDateFormat("HH:mm:ss.SSS")  // 精確到毫秒
        return  sdf.format(Date())
    }

    fun getFormatTime(format: String = ""):String{   //返回開發者指定格式的日期時間字符串
        val ft: String = format
        val sdf = if(!ft.isEmpty()) SimpleDateFormat(ft)
        else SimpleDateFormat("yyyyMMddHHmmss")
        return sdf.format(Date())
    }

}