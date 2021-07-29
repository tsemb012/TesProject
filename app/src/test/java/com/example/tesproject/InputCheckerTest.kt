package com.example.tesproject

import android.view.Gravity.apply
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.within
import org.assertj.core.api.SoftAssertions
import org.assertj.core.internal.bytebuddy.implementation.attribute.AnnotationAppender.Default.apply
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

        assertThat("TOKYO")//AND条件
            .`as`("TEXT CHECK TOKYO")//テストが失敗した時にこのラベルが表示される。
            .isEqualTo("TOKYO")
            .isEqualToIgnoringCase("tokyo")
            .isNotEqualTo("KYOTO")
            .isNotBlank
            .startsWith("TO")
            .endsWith("OKY")
            .matches("[A-Z]{5}")
            .isInstanceOf(String::class.java)

        SoftAssertions().apply{//最後までテストケースを実施して情報を集める場合は、SoftAssertionsを利用する。
            assertThat("TOKYO")
                .`as`("TEXT CHECK TOKYO")
                .isEqualTo("Hokkaido")
                .isEqualToIgnoringCase("tokyo")
                .isNotEqualTo("KYOTO")
                .isNotBlank
                .startsWith("TO")
                .endsWith("OKY")
                .matches("[A-Z]{5}")
                .isInstanceOf(String::class.java)
        }

        assertThat(3.13159)//数値のアサーション
            .isNotZero
            .isNotNegative
            .isGreaterThan(3.0)
            .isLessThanOrEqualTo(4.0)
            .isBetween(3.0,3.2)
            .isCloseTo(Math.PI,within(0.001))//円周率から誤差0.001であることを示す。
    }

    @Test
    fun isValid_givenLessThan3_returnsFalse(){//２文字では偽が返る。
        val actual = target.isValid("ab")
        //assertThat(actual, `is`(false))
        assertThat(actual).isFalse
    }

    @Test
    fun isValid_givenNumeric_returnTrue(){//３文字の英字のみで真が返る。
        val actual = target.isValid("123")
        //assertThat(actual,`is`(true))
        assertThat(actual).isTrue
    }

    @Test
    fun isValid_givenAlphaNumeric_returnsTrue(){//３文字以上の英字と数字の組み合わせで真が返る。
        val actual = target.isValid("abc123")
        //assertThat(actual,`is`(true))
        assertThat(actual).isTrue
    }

    @Test
    fun isValid_givenInvalidCharacter_returnFalse(){//3文字以上だが半角英数以外の文字が含まれると偽が返る。
        val actual = target.isValid("abc@123")
        //assertThat(actual,`is`(false))
        assertThat(actual).isFalse
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