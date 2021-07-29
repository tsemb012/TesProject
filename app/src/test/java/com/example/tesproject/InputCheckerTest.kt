package com.example.tesproject

import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
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
        val actual = target.isValid("foo")//メソッド呼び出し。
        assertThat(actual, `is`(true))//実測値と期待値が一致することを確認。
    }

    @Test
    fun isValid_givenLessThan3_returnsFalse(){//２文字では偽が返る。
        val actual = target.isValid("ab")
        assertThat(actual, `is`(false))
    }

    @Test
    fun isValid_givenNumeric_returnTrue(){//３文字の英字のみで真が返る。
        val actual = target.isValid("123")
        assertThat(actual,`is`(true))
    }

    @Test
    fun isValid_givenAlphaNumeric_returnsTrue(){//３文字以上の英字と数字の組み合わせで真が返る。
        val actual = target.isValid("abc123")
        assertThat(actual,`is`(true))
    }

    @Test
    fun isValid_givenInvalidCharacter_returnFalse(){//3文字以上だが半角英数以外の文字が含まれると偽が返る。
        val actual = target.isValid("abc@123")
        assertThat(actual,`is`(false))
    }

    @Test(expected = IllegalArgumentException::class)
        //同アノテーションを使用した場合、例外がThrowされなければテストケースは失なる。
        //このやり方では、例外が2種類以上Throwされるケースで例外メッセージを元に区別できない。
        //もし例外メッセージなども詳細に検証したい場合は、AssertJを使用する。
    fun inValid_givenNull_throwsIllegalArgumentException(){
        target.isValid(null)
    }

    @Ignore("テスト対象が仮実装なので一時的にスキップ")//Ignoreアノテーションでテストを一時的にスキップ。
    @Test
    fun temporarilySkipThisTest(){

    }






}