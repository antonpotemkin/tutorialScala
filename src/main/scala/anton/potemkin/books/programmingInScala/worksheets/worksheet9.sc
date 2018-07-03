def checkString(query: String, strings: List[String], matcher: (String, String) => Boolean): String = {
  val result = for (string <- strings; if matcher(query, string)) yield string
  result.mkString(" ")
}

val isContains = (query: String, string: String) => string.contains(query)
def isStart(query: String, string: String): Boolean = string.startsWith(query)

val strings = List("haha", "heco", "first", "Second")
println(checkString("hah", strings, isContains))
println(checkString("ec", strings, _.contains(_)))
println(checkString("f", strings, isStart))
println(checkString("f", strings, _.startsWith(_)))

def checkStringCurrying(query: String, strings: List[String])(matcher: (String, String) => Boolean): String = {
  val result = for (string <- strings if matcher(string, query)) yield string
  result.mkString(" ")
}

val hz = checkStringCurrying("co", strings) _
println(checkStringCurrying("ec", strings) {
  isContains
})
println(checkStringCurrying("ec", strings) {
  _.contains(_)
})
println(hz {
  _ contains _
})
