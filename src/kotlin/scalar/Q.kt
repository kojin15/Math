package kotlin.scalar

import java.math.BigInteger

/**
 * @author kojin15.
 */
class Q(n: Z, d: Z = Z.ONE) {
    companion object {
        val ZERO = Q(0)
        val ONE = Q(1)
    }

    val n: Z
    val d: Z

    init {
        require(d != Z.ZERO) { "division by zero" }
        if (n == BigInteger.ZERO) {
            this.n = n
            this.d = BigInteger.ONE
        } else gcd(n, d).absoluteValue.let {
            this.n = n / it * d.signum()
            this.d = d / it * d.signum()
        }

    }

    constructor(n: Int, d: Int = 1) : this(n.toBigInteger(), d.toBigInteger())

    constructor(n: Long, d: Long = 1L) : this(n.toBigInteger(), d.toBigInteger())

    constructor(n: Q, d: Q = ONE) : this(n.n * d.d, n.d * d.n)

    val absoluteValue: Q get() = Q(this.n.absoluteValue, this.d)

    val sign: Int get() = this.n.sign

    fun isFrac() = d != Z.ONE

    fun square() = this * this

    fun inverse(): Q = Q(this.d, this.n)

    operator fun unaryMinus(): Q = Q(-this.n, this.d)

    operator fun plus(other: Int): Q = Q(this.n + other + this.d, this.d)
    operator fun plus(other: Long): Q = Q(this.n + other + this.d, this.d)
    operator fun plus(other: BigInteger): Q = Q(this.n + other + this.d, this.d)
    operator fun plus(other: Q): Q =
        Q(this.n * other.d + other.n * this.d, this.d * other.d)

    operator fun minus(other: Int): Q = this + (-other)
    operator fun minus(other: Long): Q = this + (-other)
    operator fun minus(other: BigInteger): Q = this + (-other)
    operator fun minus(other: Q): Q = this + (-other)

    operator fun times(other: Int): Q = Q(this.n * other, this.d)
    operator fun times(other: Long): Q = Q(this.n * other, this.d)
    operator fun times(other: BigInteger): Q = Q(this.n * other, this.d)
    operator fun times(other: Q): Q = Q(this.n * other.n, this.d * other.d)

    operator fun div(other: Int): Q = this * other.inverse()
    operator fun div(other: Long): Q = this * other.inverse()
    operator fun div(other: BigInteger): Q = this * other.inverse()
    operator fun div(other: Q): Q = this * other.inverse()

    operator fun compareTo(other: Int): Int = (this - other).n.sign
    operator fun compareTo(other: Long): Int = (this - other).n.sign
    operator fun compareTo(other: BigInteger): Int = (this - other).n.sign
    operator fun compareTo(other: Q): Int = (this - other).n.sign

    operator fun component1() = n
    operator fun component2() = d

    override fun equals(other: Any?): Boolean = when (other) {
        is Q -> this.n == other.n && this.d == other.d
        is Int -> this == Q(other)
        is Long -> this == Q(other)
        else -> super.equals(other)
    }

    override fun hashCode(): Int {
        var result = n.hashCode()
        result = 31 * result + d.hashCode()
        return result
    }

    override fun toString(): String {
        var s = "$n"
        if (d != Z.ONE) s += "/$d"
        return s
    }
}

infix fun Q.pow(n: Int): Q = when {
    n < 0 -> this.pow(-n).inverse()
    n == 0 -> Q.ONE
    this == Q.ZERO -> Q(0)
    n % 2 == 0 -> this.pow(n / 2).let { it * it }
    else -> this * this.pow(n - 1)
}

infix fun Q.pow(n: Long): Q = when {
    n < 0 -> this.pow(-n).inverse()
    n == 0L -> Q.ONE
    this == Q.ZERO -> Q(0)
    n % 2 == 0L -> this.pow(n / 2).let { it * it }
    else -> this * this.pow(n - 1)
}

infix fun Q.pow(n: BigInteger): Q = when {
    n < BigInteger.ZERO -> this.pow(-n).inverse()
    n == BigInteger.ZERO -> Q.ONE
    this == Q.ZERO -> Q(0)
    n % 2 == BigInteger.ZERO -> this.pow(n / 2).let { it * it }
    else -> this * this.pow(n - 1)
}