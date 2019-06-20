package kotlin.vector

import kotlin.scalar.Q
import kotlin.scalar.Z

/**
 * @author kojin15.
 */
data class Vector3(val x: Q, val y: Q, val z: Q) {
    companion object {
        val ZERO = Vector3(0, 0, 0)
        val Ex = Vector3(1, 0, 0)
        val Ey = Vector3(0, 1, 0)
        val Ez = Vector3(0, 0, 1)
    }

    constructor(x: Int, y: Int, z: Int) : this(Q(x), Q(y), Q(z))
    constructor(x: Long, y: Long, z: Long) : this(Q(x), Q(y), Q(z))
    constructor(x: Z, y: Z, z: Z) : this(Q(x), Q(y), Q(z))

    fun inverse(): Vector3 = this.unaryMinus()

    operator fun unaryMinus(): Vector3 = Vector3(-this.x, -this.y, -this.z)

    operator fun plus(other: Vector3): Vector3 = Vector3(this.x + other.x, this.y + other.y, this.z + other.z)

    operator fun minus(other: Vector3): Vector3 = this + (-other)

    operator fun times(other: Int): Vector3 = Vector3(this.x * other, this.y * other, this.z * other)
    operator fun times(other: Long): Vector3 = Vector3(this.x * other, this.y * other, this.z * other)
    operator fun times(other: Z): Vector3 = Vector3(this.x * other, this.y * other, this.z * other)
    operator fun times(other: Q): Vector3 = Vector3(this.x * other, this.y * other, this.z * other)

    infix fun inner(other: Vector3): Q = this.x * other.x + this.y * other.y + this.z * other.z

    infix fun cross(other: Vector3): Vector3 = Vector3(
        this.y * other.z - this.z * other.y,
        this.z * other.x - this.x * other.z,
        this.x * other.y - this.y * other.x
    )

    override fun equals(other: Any?): Boolean {
        if (other !is Vector3) return false
        val (a1, a2, a3) = this
        val (b1, b2, b3) = other
        return a1 == b1 && a2 == b2 && a3 == b3
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + z.hashCode()
        return result
    }

    override fun toString(): String {
        return "($x, $y, $z)"
    }
}