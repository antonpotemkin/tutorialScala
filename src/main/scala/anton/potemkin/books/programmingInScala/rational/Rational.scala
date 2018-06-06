package anton.potemkin.books.programmingInScala.rational

class Rational(n: Int, d: Int) {
  require(d != 0)
  val g: Int = gcd(n.abs, d.abs)
  val numer: Int = n / g
  val denom: Int = d / g

  override def toString: String = s"$numer/$denom"

  def this(n: Int) = this(n, 1) //additional constructor

  def add(that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom, denom * that.denom
    )

  def lessThan(that: Rational): Boolean = this.numer * that.denom < this.denom * that.denom

  //  def max(that: Rational): Rational = if (this lessThan that) that else this
  def max(that: Rational): Rational = if (lessThan(that)) that else this

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  def +(that: Rational): Rational = new Rational(numer * that.denom + that.numer * denom, denom * that.denom)

  def +(that: Int): Rational = new Rational(denom * that + numer, denom)

  def -(that: Rational): Rational = new Rational(numer * that.denom - that.numer * denom, denom * that.denom)

  def -(that: Int): Rational = new Rational(numer - denom * that, denom)

  def *(that: Rational): Rational = new Rational(numer * that.numer, denom * that.denom)

  def *(that: Int): Rational = new Rational(numer * that, denom)

  def /(that: Rational): Rational = new Rational(numer * that.denom, denom * that.numer)

  def /(that: Int): Rational = new Rational(numer, denom * that)

}
