package kotlin.scalar

import java.math.BigDecimal
import java.math.BigInteger
import kotlin.math.absoluteValue

/**
 * @author kojin15.
 */

fun Double.toCorrectString(): String = BigDecimal.valueOf(this).toPlainString()
fun Float.toCorrectString(): String = BigDecimal.valueOf(this.toDouble()).toPlainString()

fun Double.toQ(): Q {
    val s = this.toCorrectString()
    if (!s.contains('.')) return Q(BigInteger(s))

    val (a, b) = s.split(".")
    val n = BigInteger(a + b)
    val d = BigInteger.TEN pow b.length
    return Q(n, d)
}

fun Int.square() = this * this

fun Long.square() = this * this

fun Int.inverse() = Q(this).inverse()

fun Long.inverse() = Q(this).inverse()

tailrec fun gcd(a: Int, b: Int): Int = when {
    b == 0 -> a.absoluteValue
    a == 0 -> b.absoluteValue
    else -> gcd(b, a % b)
}

tailrec fun gcd(a: Long, b: Long): Long = when {
    b == 0L -> a.absoluteValue
    a == 0L -> b.absoluteValue
    else -> gcd(b, a % b)
}

fun gcd(a: BigInteger, b: BigInteger): BigInteger = when {
    b == BigInteger.ZERO -> a.absoluteValue
    a == BigInteger.ZERO -> b.absoluteValue
    else -> gcd(b, a % b)
}

infix fun Int.pow(n: Int): Q = when {
    n < 0 -> this.pow(-n).inverse()
    n == 0 -> Q(1)
    this == 0 -> Q(0)
    n % 2 == 0 -> this.pow(n / 2).let { it * it }
    else -> Q(this) * this.pow(n - 1)
}

infix fun Int.pow(n: Long): Q = when {
    n < 0 -> this.pow(-n).inverse()
    n == 0L -> Q(1)
    this == 0 -> Q(0)
    n % 2 == 0L -> this.pow(n / 2).let { it * it }
    else -> Q(this) * this.pow(n - 1)
}

infix fun Int.pow(n: BigInteger): Q = when {
    n < BigInteger.ZERO -> this.pow(-n).inverse()
    n == BigInteger.ZERO -> Q(1)
    this == 0 -> Q(0)
    n % 2 == BigInteger.ZERO -> this.pow(n / 2).let { it * it }
    else -> Q(this) * this.pow(n - 1)
}


infix fun Long.pow(n: Int): Q = when {
    n < 0 -> this.pow(-n).inverse()
    n == 0 -> Q(1)
    this == 0L -> Q(0)
    n % 2 == 0 -> this.pow(n / 2).let { it * it }
    else -> Q(this) * this.pow(n - 1)
}

infix fun Long.pow(n: Long): Q = when {
    n < 0 -> this.pow(-n).inverse()
    n == 0L -> Q(1)
    this == 0L -> Q(0)
    n % 2 == 0L -> this.pow(n / 2).let { it * it }
    else -> Q(this) * this.pow(n - 1)
}

infix fun Long.pow(n: BigInteger): Q = when {
    n < BigInteger.ZERO -> this.pow(-n).inverse()
    n == BigInteger.ZERO -> Q(1)
    this == 0L -> Q(0)
    n % 2 == BigInteger.ZERO -> this.pow(n / 2).let { it * it }
    else -> Q(this) * this.pow(n - 1)
}


infix fun BigInteger.pow(n: Int): BigInteger = this.pow(n)

infix fun BigInteger.powQ(n: Int): Q = when {
    n < 0 -> this.powQ(-n).inverse()
    n == 0 -> Q(1)
    this == BigInteger.ZERO -> Q(0)
    n % 2 == 0 -> this.powQ(n / 2).let { it * it }
    else -> Q(this) * this.powQ(n - 1)
}

infix fun BigInteger.pow(n: Long): Q = when {
    n < 0 -> this.pow(-n).inverse()
    n == 0L -> Q(1)
    this == BigInteger.ZERO -> Q(0)
    n % 2 == 0L -> this.pow(n / 2).let { it * it }
    else -> Q(this) * this.pow(n - 1)
}

infix fun BigInteger.pow(n: BigInteger): Q = when {
    n < BigInteger.ZERO -> this.pow(-n).inverse()
    n == BigInteger.ZERO -> Q(1)
    this == BigInteger.ZERO -> Q(0)
    n % 2 == BigInteger.ZERO -> this.pow(n / 2).let { it * it }
    else -> Q(this) * this.pow(n - 1)
}

fun Ack(m: Z, n: Z): Z = when {
    m == Z.ZERO -> n + 1
    n == Z.ZERO -> Ack(m - 1, z(1))
    else -> Ack(m - 1, Ack(m, n - 1))
}