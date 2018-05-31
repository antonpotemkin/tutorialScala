//week1_3
// default call by value
def loop(): Int = loop()

// call by Value x and y got infloop
//def constOneLoop(x: Int, y: Int) = 1
//constOneLoop(1,loop())

//call by Value x
//call by Name y
def constOne(x: Int, y: => Int) = 1
constOne(2, loop())

//week1_4
//short-circuit evaluation - сокрещенное вычисление
def isFirstLess(a: Int, b: Int) = a < b

!true
!false
true && isFirstLess(5, 3) // isFirstLess
false && isFirstLess(4, 5) // false
false || isFirstLess(4, 5) // isFirstLess
true || isFirstLess(5, 4) // true