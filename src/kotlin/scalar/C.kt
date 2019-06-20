package kotlin.scalar

/**
 * @author kojin15.
 */
data class C(val real: Q, val imaginary: Q) {

    constructor(real: Int, imaginary: Int) : this(Q(real), Q(imaginary))
    constructor(real: Int, imaginary: Long): this(Q(real), Q(imaginary))
    constructor(real: Int, imaginary: Z): this(Q(real), Q(imaginary))
    constructor(real: Int, imaginary: Q) : this(Q(real), imaginary)

    constructor(real: Long, imaginary: Long) : this(Q(real), Q(imaginary))
    constructor(real: Long, imaginary: Int): this(Q(real), Q(imaginary))
    constructor(real: Long, imaginary: Z): this(Q(real), Q(imaginary))
    constructor(real: Long, imaginary: Q) : this(Q(real), imaginary)

    constructor(real: Z, imaginary: Z) : this(Q(real), Q(imaginary))
    constructor(real: Z, imaginary: Int): this(Q(real), Q(imaginary))
    constructor(real: Z, imaginary: Long): this(Q(real), Q(imaginary))
    constructor(real: Z, imaginary: Q) : this(Q(real), imaginary)

    constructor(real: Q, imaginary: Int) : this(real, Q(imaginary))
    constructor(real: Q, imaginary: Long): this(real, Q(imaginary))
    constructor(real: Q, imaginary: Z): this(real, Q(imaginary))

    fun isReal() = imaginary == Q.ZERO

    fun isPureImaginary() = real == Q.ZERO

    fun inverse(): C = C(
        Q(this.real, this.real.square() + this.imaginary.square()),
        Q(-this.imaginary, this.real.square() + this.imaginary.square())
    )

    operator fun unaryMinus(): C = C(-this.real, -this.imaginary)

    operator fun plus(other: Int): C = C(this.real + other, this.imaginary)
    operator fun plus(other: Long): C = C(this.real + other, this.imaginary)
    operator fun plus(other: Z): C = C(this.real + other, this.imaginary)
    operator fun plus(other: Q): C = C(this.real + other, this.imaginary)
    operator fun plus(other: C): C =
        C(this.real + other.real, this.imaginary + other.imaginary)

    operator fun minus(other: Int): C = this + (-other)
    operator fun minus(other: Long): C = this + (-other)
    operator fun minus(other: Z): C = this + (-other)
    operator fun minus(other: Q): C = this + (-other)
    operator fun minus(other: C): C = this + (-other)

    operator fun times(other: Int): C = C(this.real * other, this.imaginary * other)
    operator fun times(other: Long): C = C(this.real * other, this.imaginary * other)
    operator fun times(other: Z): C =
        C(this.real * other, this.imaginary * other)
    operator fun times(other: Q): C =
        C(this.real * other, this.imaginary * other)
    operator fun times(other: C): C =
        C(
            this.real * other.real - this.imaginary * other.imaginary,
            this.imaginary * other.real + this.real * other.imaginary
        )

    operator fun div(other: Int): C = this * other.inverse()
    operator fun div(other: Long): C = this * other.inverse()
    operator fun div(other: Z): C = this * other.inverse()
    operator fun div(other: Q): C = this * other.inverse()
    operator fun div(other: C): C = this * other.inverse()

    override fun equals(other: Any?): Boolean = when (other) {
        is C -> this.real == other.real && this.imaginary == other.imaginary
        is Q -> other == this.real && this.imaginary == Q.ZERO
        is Int -> other == Q(other) && this.imaginary == Q.ZERO
        is Long -> other == Q(other) && this.imaginary == Q.ZERO
        else -> super.equals(other)
    }

    override fun hashCode(): Int {
        var result = real.hashCode()
        result = 31 * result + imaginary.hashCode()
        return result
    }

    override fun toString(): String {
        val r = when {
            real == Q.ZERO -> ""
            !real.isFrac() || imaginary == Q.ZERO -> "$real"
            else -> "($real)"
        }

        val s = when {
            imaginary < Q.ZERO -> "-"
            imaginary > Q.ZERO -> "+"
            else -> ""
        }

        val i = when {
            imaginary == Q.ZERO -> ""
            imaginary == Q.ONE || imaginary == -Q.ONE -> "i"
            !imaginary.isFrac() -> "${imaginary.absoluteValue}i"
            else -> "(${imaginary.absoluteValue})i"
        }

        return (r + s + i).let { if (it.isBlank()) "0" else it }
    }

}