
val x = 1
val y = 2
println(x+y)

// 单表达式函数
fun theAnswer() = 42
// 等价于
fun theAnswer2(): Int {
    return 42
}

// 1.空值与 null 检测
// 当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空。
fun parseInt(value: String) : Int? {
    println(value)
    if (value.isEmpty()) return null
    return value.toInt();
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    // 直接使用 `x * y` 会导致编译错误，因为它们可能为 null
    if (x != null && y != null) {
        // 在空检测后，x 与 y 会自动转换为非空值（non-nullable）
        println(x * y)
    }
    else {
        println("'$arg1' or '$arg2' is not a number")
    }
}

printProduct("2","3")

// 2.类型检测与自动类型转换

fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // `obj` 在该条件分支内自动转换成 `String`
        return obj.length
    }

    // 在离开类型检测分支后，`obj` 仍然是 `Any` 类型
    return null
}

fun getStringLength2(obj: Any): Int? {
    if (obj !is String) return null

    // `obj` 在这一分支自动转换为 `String`
    return obj.length
}

//  for循环
val items = listOf("apple","banana","orange")
for (item in items) {
    println("for循环 $item")
}

for (index in items.indices) {
    println("for循环 $index is ${items[index]}")
}

for (x in 1..5) {
    print(x)   // 打印出12345
}
// 数列迭代
for (x in 1..10 step 2) {
    print(x)   // 打印出13579
}
println()
for (x in 9 downTo 0 step 3) {
    print(x)   // 打印出9630
}

//  while循环
var index = 0
while (index < items.size) {
    println("while循环 $index is ${items[index]}")
    index++;
}

//  when表达式, 类型判断
fun describe(obj: Any): String =
    when (obj) {
        1 -> "One"
        "Hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a string"
        else -> "Unknown"
    }

fun transform(color: String): Int = when (color) {
    "Red" -> 0
    "Green" -> 1
    "Blue" -> 2
    else -> throw IllegalArgumentException("Invalid color param value")
}

println(describe(1))
println(describe("Hello"))
println(describe(1000L))
println(describe(2))
println(describe("other"))

when {
    "orange" in items -> println("juicy")  // juicy
    "apple" in items -> println("apple is fine too")
}

// 3. 使用 in 运算符来检测某个数字是否在指定区间内：
for (i in 1..100) { }       // 闭区间：包含 100
for (i in 1 until 100) { }  // 半开区间：不包含 100
if (x in 1..y+1) {
    println("fits in range")
}

val list = listOf("a","b","c") // 只读list
// 检测某个数字是否在指定区间外:
if (-1 !in 0..list.lastIndex) {
    println("-1 is out of range")
}
if (list.size !in list.indices) {
    println("list size is out of valid list indices range, too")
}

val map = mapOf("a" to 1, "b" to 2, "c" to 3) // 只读map
println(map["b"])  // 2
// 遍历 map/pair型list
for ((k,v) in map)
{
    println("$k --> $v")
}

// 过滤 list
val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
fruits
    .filter { it.startsWith("a") }
    .sortedBy { it }
    .map { it.toUpperCase() }
    .forEach { println(it) }
// 打印出 APPLE  AVOCADO


val list2 = listOf(1,2,3,4,5);
val positives = list2.filter { x -> x > 3 }
val positives2 = list2.filter { it > 3 }
println(positives)   //[4, 5]
println(positives2)  //[4, 5]

// 4.延迟属性
class People {
    val age:Int by lazy {
        // 计算年龄
        5
    };
    var name:String = ""
}

val p = People()
println(p.age)   // 5

// 使用作用域函数 apply/with/run/also/let
// apply 配置未出现在对象构造函数中的属性
val p2 = People().apply {
    name = "sk"
}

// 5.扩展函数
fun String.spaceToCamelCase() {
    println(this)
    println("spaceToCamelCase")
}
"Convert this to camelcase".spaceToCamelCase()

// 交换两个变量  TODO("将代码标记为不完整")
var a = 1
var b = 2
a = b.also { b = a }

// 6.创建单例
object CacheManager {
    val dbName = "netcache"
}
val cacheManager = CacheManager;
println("cacheManager.dbName : ${cacheManager.dbName}")


// 7.try/catch
fun test() {
    val result = try {

    } catch (e: ArithmeticException) {
        throw IllegalStateException(e)
    }
    // 使用 result
}