package com.example.tesproject

import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class InputCheckerTest {
    lateinit var target: InputChecker

    @Before
    fun setUp() {//毎回クラスをインスタンス化するのは手間なので、Beforeで初期化処理を共通化//メソッド名は慣習的にsetUp
        target = InputChecker()
    }

    @After
    fun tearDown() {//必要ならば後処理をtearDown()メソッドに記述する。
    }

    @Test
    fun isValid() {
        //val target = InputChecker()//インスタンス生成。
        val actual = target.isValid("foo")//メソッド呼び出し。
        assertThat(actual, `is`(true))//実測値と期待値が一致することを確認。
    }

    @Test
    fun isValid_givenLessThan3_returnsFalse(){
        //val target = InputChecker()
        val actual = target.isValid("ab")
        assertThat(actual, `is`(false))
    }

    @Test
    fun isValid_givenAlphaNumeric_returnsTrue(){
        //val target = InputChecker()
        val actual = target.isValid("abc123")
        assertThat(actual,`is`(true))
    }

}