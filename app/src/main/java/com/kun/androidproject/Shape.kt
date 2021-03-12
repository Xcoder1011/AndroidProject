package com.kun.androidproject


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

//    val name: String = MyJavaApi.getProperty("name")
    companion object {
        fun fromPolar(angle: Double, radius: Double) = Point(x = angle, y = radius)
    }
}