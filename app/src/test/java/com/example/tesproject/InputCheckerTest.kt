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

    @Test(expected = IllegalArgumentException::class)
        //同アノテーションを使用した場合、例外がThrowされなければテストケースは失敗となる。
        //このやり方では、例外が2種類以上Throwされるケースで例外メッセージを元に区別できない。
        //もし例外メッセージなども詳細に検証したい場合は、AssertJを使用する。
    fun inValid_givenNull_throwsIllegalArgumentException(){
        target.isValid(null)
    }



}