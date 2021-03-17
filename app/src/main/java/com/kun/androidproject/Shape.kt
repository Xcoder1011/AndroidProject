package com.kun.androidproject

import org.w3c.dom.Element

/*
类布局
通常，一个类的内容按以下顺序排列：

属性声明与初始化块
次构造函数
方法声明
伴生对象
*/

abstract class Shape(val sides: List<Double>) {
    val perimeter: Double get() = sides.sum()
    abstract fun caclateArea(): Double
}

interface RectangleProperties {
    val isSquare: Boolean
}

class Rectangle(
    var height: Double,
    var length: Double
) : Shape(listOf(height, length, height, length)), RectangleProperties {

    override val isSquare: Boolean
        get() = length === height

    override fun caclateArea(): Double {
        return height * length
    }
}

class Triangle(
    var sideA: Double,
    var sideB: Double,
    var sideC: Double
) : Shape(listOf(sideA, sideB, sideC)) {

    override fun caclateArea(): Double {
        val s = perimeter / 2
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC))
    }
}

//=============================================================================


// 工厂函数
class Point(val x: Double, val y: Double) {

    // 使用平台类型表达式初始化的属性（包级别或类级别）必须显式声明其 Kotlin 类型
    // val name: String = MyJavaApi.getProperty("name")

    companion object {
        // 如果为一个类声明一个工厂函数，那么不要让它与类自身同名。优先使用独特的名称，
        fun fromPolar(angle: Double, radius: Double) = Point(x = angle, y = radius)
    }
}

//一个类有两个概念上相同的属性，一个是公共 API 的一部分，另一个是实现细节，那么使用下划线作为私有属性名称的前缀：

class C {

    private val _elementList = mutableListOf<Element>()

    val elementList: List<Element>
        get() = _elementList
}

//=============================================================================

open class Rectangle3 {
    open fun caculateArea() { }
}

interface Shape2 {
    val vertexCount: Int
    fun caculateArea() { }
}

// 可以在主构造函数中使用 override 关键字作为属性声明的一部分。
class Rectangle2(override val vertexCount: Int = 4) : Shape2

class Polygon2 : Shape2 {
    override var vertexCount: Int = 1   // 可以用一个 var 属性覆盖一个 val 属性，但反之则不行，以后可以设置为任何数
}

// 可以同时继承 Rectangle3 与 Shape2， 但是二者都有各自的 caculateArea() 实现，
// 所以我们必须在 Square 中覆盖 caculateArea()， 并提供其自身的实现以消除歧义。
abstract class Polygon4() : Rectangle3(), Shape2 {
    override fun caculateArea() {
        super<Rectangle3>.caculateArea()
        super<Shape2>.caculateArea()
    }
}

abstract class ClassB : Rectangle3() {
    // 我们可以用一个抽象成员覆盖一个非抽象的开放成员
    abstract override fun caculateArea()
}

//=============================================================================
