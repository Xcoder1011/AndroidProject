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


// 工厂函数
class Point(val x: Double, val y: Double) {

    // val name: String = MyJavaApi.getProperty("name")
    companion object {
        fun fromPolar(angle: Double, radius: Double) = Point(x = angle, y = radius)
    }
}

//一个类有两个概念上相同的属性，一个是公共 API 的一部分，另一个是实现细节，那么使用下划线作为私有属性名称的前缀：

class C {

    private val _elementList = mutableListOf<Element>()

    val elementList: List<Element>
        get() = _elementList
}
