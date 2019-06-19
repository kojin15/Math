package kotlin

import java.math.BigInteger

/**
 * @author kojin15.
 */
typealias Z = BigInteger
fun z(x: Int) = x.toBigInteger()
fun z(x: Long) = x.toBigInteger()

val Z.absoluteValue: BigInteger get() = this.abs()
val Z.sign: Int get() = this.signum()

fun Z.square() = this * this

fun Z.inverse(): Q = Q(this).inverse()

operator fun BigInteger.plus(x: Int): BigInteger = this + x.toBigInteger()
operator fun BigInteger.plus(x: Long): BigInteger = this + x.toBigInteger()
operator fun BigInteger.plus(x: BigInteger): BigInteger = this.add(x)

operator fun BigInteger.minus(x: Int): BigInteger = this - x.toBigInteger()
operator fun BigInteger.minus(x: Long): BigInteger = this - x.toBigInteger()
operator fun BigInteger.minus(x: BigInteger): BigInteger = this.subtract(x)

operator fun BigInteger.times(x: Int): BigInteger = this * x.toBigInteger()
operator fun BigInteger.times(x: Long): BigInteger = this * x.toBigInteger()
operator fun BigInteger.times(x: BigInteger): BigInteger = this.multiply(x)

operator fun BigInteger.div(x: Int): BigInteger = this / x.toBigInteger()
operator fun BigInteger.div(x: Long): BigInteger = this / x.toBigInteger()
operator fun BigInteger.div(x: BigInteger): BigInteger = this.divide(x)

operator fun BigInteger.rem(x: Int): BigInteger = this % x.toBigInteger()
operator fun BigInteger.rem(x: Long): BigInteger = this % x.toBigInteger()
operator fun BigInteger.rem(x: BigInteger): BigInteger = this.mod(x)

operator fun BigInteger.unaryMinus(): BigInteger = this.negate()