//week1_3
// default call by-value
def loop: Boolean = loop

// call by-value x and y got infinite loop
//def constOneLoop(x: Int, y: Int) = 1
//constOneLoop(1,loop())

//call by-value x
//call by-name y
def constOne(x: Int, y: => Boolean) = 1
constOne(2, loop)

//week1_4
//short-circuit evaluation - сокрещенное вычисление
def isFirstLess(a: Int, b: Int) = a < b

!true
!false
true && isFirstLess(5, 3) // isFirstLess
false && isFirstLess(4, 5) // false
false || isFirstLess(4, 5) // isFirstLess
true || isFirstLess(5, 4) // true

//function def call by-name
//function val call by-value
def x = loop // it's OK
//val y = loop // infinite loop
