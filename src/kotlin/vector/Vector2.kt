package kotlin.vector

import kotlin.scalar.Q
import kotlin.scalar.Z

/**
 * @author kojin15.
 */
data class Vector2(val x: Q, val y: Q) {
    companion object {
        val ZERO = Vector2(0, 0)
        val Ex = Vector2(1, 0)
        val Ey = Vector2(0, 1)
    }

    constructor(x: Int, y: Int) : this(Q(x), Q(y))
    constructor(x: Int, y: Long): this(Q(x), Q(y))
    constructor(x: Int, y: Z): this(Q(x), Q(y))
    constructor(x: Int, y: Q) : this(Q(x), y)

    constructor(x: Long, y: Long) : this(Q(x), Q(y))
    constructor(x: Long, y: Int): this(Q(x), Q(y))
    constructor(x: Long, y: Z): this(Q(x), Q(y))
    constructor(x: Long, y: Q) : this(Q(x), y)

    constructor(x: Z, y: Z) : this(Q(x), Q(y))
    constructor(x: Z, y: Int): this(Q(x), Q(y))
    constructor(x: Z, y: Long): this(Q(x), Q(y))
    constructor(x: Z, y: Q) : this(Q(x), y)

    constructor(x: Q, y: Int) : this(x, Q(y))
    constructor(x: Q, y: Long): this(x, Q(y))
    constructor(x: Q, y: Z): this(x, Q(y))

    fun inverse(): Vector2 = this.unaryMinus()

    operator fun unaryMinus(): Vector2 = Vector2(-this.x, -this.y)

    operator fun plus(other: Vector2): Vector2 = Vector2(this.x + other.x, this.y + other.y)

    operator fun minus(other: Vector2): Vector2 = this + (-other)

    operator fun times(other: Int): Vector2 = Vector2(this.x * other, this.y * other)
    operator fun times(other: Long): Vector2 = Vector2(this.x * other, this.y * other)
    operator fun times(other: Z): Vector2 = Vector2(this.x * other, this.y * other)
    operator fun times(other: Q): Vector2 = Vector2(this.x * other, this.y * other)

    infix fun inner(other: Vector2): Q = this.x * other.x + this.y * other.y

    override fun equals(other: Any?): Boolean {
        if (other !is Vector2) return false
        val (a1, a2) = this
        val (b1, b2) = other
        return a1 == b1 && a2 == b2
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }

    override fun toString(): String {
        return "($x, $y)"
    }
}