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

const val MAX_COUNT = 8
val STUDENT_NAME_FIELD = "StudentName"

class Student {

    @Named("telePhone")
    private val phone: String = ""

    // 如果函数的表达式函数体与函数声明不适合放在同一行，那么将 = 留在第一
    fun f(x: String, y: String, z: String) =
        veryLongFunctionCallWithManyWords(x, y, z)

    fun veryLongFunctionCallWithManyWords(x: String, y: String, z: String) {

    }

}



//abstract class Cache {
//
//}
//
//abstract class UUCache<out T : Any> : Cache {
//    abstract fun getCache(key: String): T
//}
//
//class UUCacheImpl : UUCache() {
//    constructor(x: String) : this(x) {
//
//    }
//
//    val x = object : Cache {
//
//    }
//}

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
