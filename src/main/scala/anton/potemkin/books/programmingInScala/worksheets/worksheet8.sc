//chapter8
val increase1 = (x: Int) => x + 1
increase1(1)
val increase2 = (x: Int) => {
  println("Haha")
  x + 1
}
val list = List(1, 2, 4, 5, -5, -4, 0, 1, 100)
list.foreach((x) => println(x))
list.foreach(x => println(x))
list.foreach(println)

list.filter(x => x > 0)
list.filter(_ > 0)
val f = (_: Int) + (_: Int)
f(1, 4)
list.filter(_ < 0).foreach(println _)
def sum(a: Int, b: Int, c: Int) = a + b + c
sum(1, 2, 3)
val a = sum _
a(1, 2, 3)
a.apply(1, 2, 3)
val b = sum(1, _: Int, 3)
b(2)
val more = 4
def addMoreDef(x: Int) = x + more
addMoreDef(1)

//открытый терм
//функциональный литерал со свободными переменными
val addMore = (x: Int) => x + more
addMore(1)
//закрытый терм
val addTwo = (x: Int) => x + 2
addTwo(3)

var term = 10
val addTerm = (x: Int) => x + term
addTerm(1)
term = 100
addTerm(1)

def makeIncreaser(more: Int) = (x: Int) => x + more
val inc1 = makeIncreaser(1)
val inc10 = makeIncreaser(10)
inc1(1)
inc10(1)

def echo(str: String, args: String*) = {
  println(s"First param is $str")
  for (arg <- args)
    println(arg)
}
echo("first")
echo("first", "second", "third")

val arr = Array("What's", "up", "doc")
echo("string", arr: _*)

def speed(distance: Float, time: Float = 5): Float = distance / time
speed(100,10)
speed(time = 10, distance = 60)
speed(100)
// Сначала позиционные, затем именованные
sum(1, c = 3, b = 2)
