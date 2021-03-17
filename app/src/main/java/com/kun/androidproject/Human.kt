package com.kun.androidproject

interface HumanProperties {
    val isOld: Boolean
}

open class Human(id: String, name: String)

class Person(
    id: String,
    name: String,
    gender: Int
) : Human(id, name),
    HumanProperties {

    override val isOld: Boolean
        get() = false
}

class Student {

}

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
