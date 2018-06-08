package anton.potemkin.books.programmingInScala.rational

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RationalTest extends FunSuite{
  val first = new Rational(1, 2)
  val second = new Rational(2, 5)

  test("Test *") {
    val mul = first * second
    val expected = new Rational(1, 5)
    assert(mul.numer == expected.numer)
    assert(mul.denom == expected.denom)
  }

  test("Test +") {
    val mul = first + second
    val expected = new Rational(9, 10)
    assert(mul.numer == expected.numer)
    assert(mul.denom == expected.denom)
  }

  test("Test -") {
    val mul = first - second
    val expected = new Rational(1, 10)
    assert(mul.numer == expected.numer)
    assert(mul.denom == expected.denom)
  }

  test("Test /") {
    val mul = first / second
    val expected = new Rational(5, 4)
    assert(mul.numer == expected.numer)
    assert(mul.denom == expected.denom)
  }

}
