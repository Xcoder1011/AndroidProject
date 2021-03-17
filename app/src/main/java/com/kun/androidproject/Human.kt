package com.kun.androidproject

import android.content.Context
import android.util.AttributeSet
import android.view.View


class Empty

// 一个类可以有一个主构造函数以及一个或多个次构造函数
class Person1 constructor(name: String) {
    val firstName = name.toUpperCase()

    val firstProperty = "First property: $name".also(::println)
    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)
    init {
        println("Second initializer block that prints ${name.length}")
    }
}

/// 次构造函数:

class Person2 {

    // 次构造函数
    var children: MutableList<Person3> = mutableListOf()

    constructor(parent: Person3) {  //可以声明前缀有constructor的次构造函数：
        parent.children.add(this)
    }
}

// 如果主构造函数没有任何注解或者可见性修饰符，可以省略这个 constructor 关键字。
class Person3(name: String) {

    // 次构造函数
    var children: MutableList<Person2> = mutableListOf()

    // 如果类有一个主构造函数，每个次构造函数需要委托给主构造函数
    // 委托到同一个类的另一个构造函数用 this 关键字即可：
    constructor(nickName: String, parent: Person2) : this(nickName) {
        parent.children.add(this)
    }
}

// 声明属性以及从主构造函数初始化属性，Kotlin 有简洁的语法：
class Person4(val firstName: String, val lastName: String, var age: Int) { }

class Customer public constructor(name: String) { /*……*/ }  // 默认public

class Customer2(val customerName: String = "")

// 如果一个非抽象类没有声明任何（主或次）构造函数
// 它会有一个生成的不带参数的主构造函数。构造函数的可见性是 public
// 如果你不希望你的类有一个公有构造函数，你需要声明一个带有非默认可见性的空的主构造函数：
class DontCreateMe private constructor () { /*……*/ }

//=============================================================================

interface HumanProperties {
    val isOld: Boolean
}

// 要使一个类可继承，请用 open 关键字标记它。
open class Human(id: String, name: String) {

    open val age: Int = 0

    open fun read() { }
    open fun write() { }
}

class Person(
    id: String,
    name: String,
    gender: Int
) : Human(id, name),
    HumanProperties {

    override val isOld: Boolean
        get() = false

    override val age: Int   // 每个声明的属性可以由具有初始化器的属性或者具有 get 方法的属性覆盖。
        get() = super.age

    override fun read() { // 标记为 override 的成员本身是开放的，也就是说，它可以在子类中覆盖
        super.read()  // 调用超类
    }

    final override fun write() {  // 如果想禁止再次覆盖，使用 final 关键字：
        super.write()
    }

    /// 在一个 内部类 中访问外部类的超类，可以通过由外部类名限定的 super 关键字来实现：super@Outer：
    inner class School {
        fun _toSchool() {
            println("toSchool")
        }
        fun toSchoolRead() {
            super@Person.read()  // 调用 Person 的 read() 实现
            _toSchool()
        }
    }
}

class MyView : View {
    // 如果派生类没有主构造函数，那么每个次构造函数必须使用 super 关键字初始化其基类型
    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
}

//=============================================================================

// 派生类初始化顺序
// 第一步完成其基类的初始化
// 设计一个基类时，应该避免在构造函数、属性初始化器以及 init 块中使用 open 成员。

/*
Constructing Derived("hello", "world")
Argument for Base: Hello
Initializing Base
Initializing size in Base: 5
Initializing Derived
Initializing size in Derived: 10
 */

open class Base(val name: String) {

    init { println("Initializing Base") }

    open val size: Int =
        name.length.also { println("Initializing size in Base: $it") }
}

class Derived(
    name: String,
    val lastName: String
) : Base(name.capitalize().also { println("Argument for Base: $it") }) {

    init { println("Initializing Derived") }

    override val size: Int =
        (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}

//=============================================================================

class Person5(
    val firstName: String,
    val lastName: String,
    val age: Int, // trailing comma

    val sum: (Int, Int, Int) -> Int = fun(
        x,
        y,
        z
    ): Int {
        return x + y + x
    }

    // println(sum(8, 8, 8))
)

enum class Direction {
    NORTH,
    SOUTH,
    WEST,
    EAST, // trailing comma
}

class Surface {
    operator fun get(x: Int, y: Int) = 2 * x + 4 * y - 10
}

fun getZValue(mySurface: Surface, xValue: Int, yValue: Int) =
    mySurface[
            xValue,
            yValue
    ]

fun main() {
    val x = { x: Comparable<Number>,
              y: Iterable<Number>
        ->
        println("1")
    }

    println(x)
}

fun <T1, T2> foo() {}

fun test2() {
    foo<
            Comparable<Number>,
            Iterable<Number>,
            >()
}

class MyMap<
        MyKey,
        MyValue
        > {}

data class Car(val manufacturer: String, val model: String, val year: Int)
val myCar = Car("Tesla", "Y", 2019)


/*
如果一个声明有多个修饰符，请始终按照以下顺序安放：

public / protected / private / internal
expect / actual
final / open / abstract / sealed / const
external
override
lateinit
tailrec
vararg
suspend
inner
enum / annotation / fun // 在 `fun interface` 中是修饰符
        companion
inline
infix
operator
data

 */
