package com.example.tesproject

class InputChecker {

    fun isValid(text:String?):Boolean{
        if (text == null) throw IllegalArgumentException("Cannot be null")//検証のため、例外を発生させる。
        return text.length >= 3 && text.matches(Regex("[a-zA-Z0-9]+"))//指定の条件にマッチした場合。

        return false
    }

}
