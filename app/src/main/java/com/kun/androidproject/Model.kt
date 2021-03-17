package com.kun.androidproject

const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"

class Model {

    var initialized = 1 // 类型 Int、默认 getter 和 setter

    val size = 1 // 类型 Int 、默认 getter

    val isEmpty: Boolean
        get() = this.size == 0

    var stringRepresentation: String
        get() = this.toString()
        set(value) {
            this.stringRepresentation = value
        }

    var setterVisibility: String = "abc"
        private set

    var counter = 0 // 注意：这个初始器直接为幕后字段赋值
        set(value) {
            if (value >= 0) field = value  // field 标识符只能用在属性的访问器内。
        }


    /// 幕后属性
    private var _table: Map<String, Int>? = null
    public val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap() // 类型参数已推断出
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }
}

// 属性既可以用关键字 var 声明为可变的，也可以用关键字 val 声明为只读的。
class Address {
    var name: String = "Holmes, Sherlock"
    var street: String = "Baker"
    var city: String = "London"
    var state: String? = null
    var zip: String = "123456"
}

fun copyAddress(address: Address): Address {
    val result = Address() // Kotlin 中没有“new”关键字
    result.name = address.name // 将调用访问器
    result.street = address.street
    // ……
    return result
}

//=============================================================================
// 接口中的属性

interface MyInterface {
    val prop: Int // 抽象的

    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        print(prop)
    }
}

class Child : MyInterface {
    override val prop: Int = 29
}

//=============================================================================
// 接口继承

interface Named {
    val name: String
}

interface Person6 : Named {
    val firstName: String
    val lastName: String

    override val name: String get() = "$firstName $lastName"
}

data class Employee(
    // 不必实现“name”
    override val firstName: String,
    override val lastName: String,
    val position: String
) : Person6