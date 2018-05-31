// default call by value
def loop(): Int = loop()

// call by Value x and y got infloop
//def constOneLoop(x: Int, y: Int) = 1
//constOneLoop(1,loop())

//call by Value x
//call by Name y
def constOne(x: Int, y: => Int) = 1
constOne(2, loop())
