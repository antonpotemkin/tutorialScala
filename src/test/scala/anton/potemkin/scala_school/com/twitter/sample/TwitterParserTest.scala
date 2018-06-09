package anton.potemkin.scala_school.com.twitter.sample

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TwitterParserTest extends FunSuite {

  val tweet = """{"id":1,"text":"foo"}"""
  val parser = new SimpleParser()

  test("first test") {
    val expected = Some(SimpleParsed(1, "foo"))
    assert(parser.parse(tweet) == expected)

    //    val expected = SimpleParsed(1,"foo")
    //    new SimpleParser().parse(tweet) match {
    //      case Some(parsed) => {
    //        assert(parsed.text == expected.text)
    //        assert(parsed.id  == expected.id)
    //      }
    //      case _ => fail("didn't parse tweet")
    //    }
  }

  test("ignore nested content") {
    val tweet = """{"id":1,"text":"foo","nested":{"id":2}}"""
    val expected = SimpleParsed(1, "foo")
    parser.parse(tweet) match {
      case Some(parsed) => {
        assert(parsed.text == expected.text)
        assert(parsed.id == expected.id)
      }
      case _ => fail("didn't parse tweet")
    }
  }

  test("reject a non-JSON tweet") {
    val tweet = """"id":1"text":"foo""""
    parser.parse(tweet) match {
      case Some(parsed) => fail("didn't reject a non-JSON tweet")
      case e => assert(e.isEmpty)
    }
  }

  test("fail on partial content") {
    val tweet = """{"id":1}"""
    parser.parse(tweet) match {
      case Some(parsed) => fail("didn't reject a partial tweet")
      case e => assert(e.isEmpty)
    }
  }
}
