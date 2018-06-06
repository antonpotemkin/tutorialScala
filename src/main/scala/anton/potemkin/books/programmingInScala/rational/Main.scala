package anton.potemkin.books.programmingInScala.rational


object Main extends App {

  implicit def intToRational(x : Int) = new Rational(x)

  println(new Rational(3, 5))
  println(new Rational(6, 9))
  println(new Rational(5))
  //  println(new Rational(3,0)) throw IllegalArgumentException
  val first = new Rational(1, 2)
  val second = new Rational(2, 3)
  println(first add second)
  println(s"first less than second: ${first lessThan second}")
  println(s"max of first and second is : ${first max second}")
  println(first + second)
  println(first.+(second))
  println(first * second)
  println(first.*(second))
  println(second + first * second)
  println(second + (first * second))
  println((second + first) * second)
  println(first + 2)
  println(first * 2)
  println(2 * first)

}
