package anton.potemkin.scala_school.com.twitter.sample

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TwitterParserTest extends FunSuite {

  val tweet = """{"id":1,"text":"foo"}"""
  test("first test") {
    val expected = Some(SimpleParsed(1,"foo"))
    assert(new SimpleParser().parse(tweet) == expected)
  }
}
