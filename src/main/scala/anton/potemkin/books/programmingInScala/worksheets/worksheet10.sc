import anton.potemkin.books.programmingInScala.chapter10.Element
val arr = Element.elem(Array(",", "3"))
arr.contents

class Cat {
  val dangerous = false
}

class Tiger(
             override val dangerous: Boolean,
             var age: Int) extends Cat
class Tiger2(param1: Boolean, param2: Int) extends Cat {
  override val dangerous = param1
  private var age = param2
}
val line = Element.elem("newLine")

val function = (message: String) => message

def method(message: String): String = message