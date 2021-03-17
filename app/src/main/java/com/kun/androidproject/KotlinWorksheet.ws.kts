
val one = 1 // Int
val threeBillion = 3000000000 // Long
val oneLong = 1L // Long   如需显式指定 Long 型值，请在该值后追加 L 后缀
val oneByte: Byte = 1

// 类型	    大小（比特数）	有效数字比特数	    指数比特数	十进制位数
// Float	32	        24	            8	        6-7
// Double	64	        53	            11	        15-16

val pi = 3.14 // Double  以小数初始化的变量，编译器会推断为 Double 类型
val e = 2.7182818284 // Double
// 如需将一个值显式指定为 Float 类型，请添加 f 或 F 后缀
// 如果这样的值包含多于 6～7 位十进制数，那么会将其舍入。
val eFloat = 2.7182818284f // Float，实际值为 2.7182817

// 可以使用下划线使数字常量更易读：
val oneMillion = 1_000_000
val creditCardNumber = 1234_5678_9012_3456L
val socialSecurityNumber = 999_99_9999L
val hexBytes = 0xFF_EC_DE_5E
val bytes = 0b11010010_01101001_10010100_10010010

/*

每个数字类型支持如下的转换:

toByte(): Byte
toShort(): Short
toInt(): Int
toLong(): Long
toFloat(): Float
toDouble(): Double
toChar(): Char

// 假想的代码，实际上并不能编译：
val a: Int? = 1 // 一个装箱的 Int (java.lang.Integer)
val b: Long? = a // 隐式转换产生一个装箱的 Long (java.lang.Long)
print(b == a) //  这将输出“false” 鉴于 Long 的 equals() 会检测另一个是否也为 Long

 */


val x2 = 5 / 2   // 整数间的除法总是返回整数。会丢弃任何小数部分。
println(x2 == 2)  // true

val x1 = 5 / 2.toDouble()  // 如需返回浮点类型，请将其中的一个参数显式转换为浮点类型。
println(x1 == 2.5) // true

// 位运算
val x = (1 shl 2) and 0x000FF000
/*
这是完整的位运算列表（只用于 Int 与 Long）：

shl(bits) – 有符号左移
shr(bits) – 有符号右移
ushr(bits) – 无符号右移
and(bits) – 位与
or(bits) – 位或
xor(bits) – 位异或
inv() – 位非

*/

// Kotlin中数组是不型变的（invariant）。
// 这意味着 Kotlin 不让我们把 Array<String> 赋值给 Array<Any>，
// 以防止可能的运行时失败（但是你可以使用 Array<out Any>, 参见类型投影）。

// 原生类型数组
val nums: IntArray = intArrayOf(1,2,3)
nums[0] = nums[1] + nums[2]
print(nums)

// 大小为 5、值为 [0, 0, 0, 0, 0] 的整型数组
val array = IntArray(5);

// 例如：用常量初始化数组中的值
// 大小为 5、值为 [42, 42, 42, 42, 42] 的整型数组
val array2 = IntArray(5) { 42 }

// 例如：使用 lambda 表达式初始化数组中的值
// 大小为 5、值为 [0, 1, 2, 3, 4] 的整型数组（值初始化为其索引值）
val array3 = IntArray(5) {it * 1}

val s = "abc" + 1
println(s + "def")   // 输出 abc1def


// 单表达式函数
fun theAnswer() = 42 // 推荐
// 等价于
fun theAnswer2(): Int {
    return 42
}

// 在 Kotlin 中，if是一个表达式，即它会返回一个值
// 因此就不需要三元运算符（条件 ? 然后 : 否则）
val max = if (x1 > x2) x1 else x2

val max2 = if (x1 > x2) {
    print("Choose x1")
    x1   // if 的分支可以是代码块，最后的表达式作为该块的值
} else {
    print("Choose x2")
    x2
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
//  取代了类 C 语言的 switch 语句
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

when (x) {
    1 -> print("x == 1")
    2 -> print("x == 2")
    3, 4, 5 -> print("x == 3, 4, 5 ")
    in 1..10 -> print("x is in the range")
    in nums -> print("x is valid")
    !in 10..20 -> print("x is outside the range")
    else -> { // 注意这个块
        print("otherwise")
    }
}

// 3. 使用 in 运算符来检测某个数字是否在指定区间内：
for (i in 1..100) { }       // 闭区间：包含 100
for (i in 1 until 100) { }  // 半开区间：不包含 100
if (x in 1..10+1) {
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

/*
 * 优先使用不可变（而不是可变）数据。初始化后未修改的局部变量与属性，总是将其声明为 val 而不是 var 。
 * 总是使用不可变集合接口（Collection, List, Set, Map）来声明无需改变的集合
 * 尽可能选用返回不可变集合类型的函数：
 */

// 不良：使用可变集合类型作为无需改变的值
fun validateValue(actualValue: String, allowedValues2: HashSet<String>) {  }

// 良好：使用不可变集合类型
fun validateValue2(actualValue: String, allowedValues: Set<String>) {  }

// 不良：arrayListOf() 返回 ArrayList<T>，这是一个可变集合类型
val allowedValues = arrayListOf("a", "b", "c")

// 良好：listOf() 返回 List<T>
val allowedValues2 = listOf("a", "b", "c")

/// 返回到标签:

fun foo() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return // 非局部直接返回到 foo() 的调用者
        print(it)
    }
    println("this point is unreachable")
    // 最终输出 12
}

// 如果我们需要从 lambda 表达式中返回，我们必须给它加标签并用以限制 return。
fun foo2() {
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
        print(it)
    }
    print(" done with explicit label")
    // 最终输出 1245 done with implicit label
}

// 等价于

fun foo3() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
        print(it)
    }
    print(" done with implicit label")
    // 最终输出 1245 done with implicit label
}

// 或者我们用一个匿名函数替代 lambda 表达式
fun foo4() {
    listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
        if (value == 3) return  // 局部返回到匿名函数的调用者，即 forEach 循环
        print(value)
    })
    print(" done with anonymous function")
    // 最终输出 1245 done with anonymous function
}

fun foo5() {
    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop // 从传入 run 的 lambda 表达式非局部返回
            print(it)
        }
    }
    print(" done with nested loop")
    // 最终输出 12 done with nested loop
}