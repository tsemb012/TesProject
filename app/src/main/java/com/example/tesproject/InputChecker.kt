package com.example.tesproject

class InputChecker {

    fun isValid(text:String?):Boolean{
        if (text == null) return false//Nullだった場合は偽と一旦判断する。
        return text.length >= 3 && text.matches(Regex("[a-zA-Z0-9]+"))//指定の条件にマッチした場合。

        return false
    }

}
