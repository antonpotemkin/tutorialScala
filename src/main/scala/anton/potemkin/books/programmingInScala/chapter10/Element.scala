package anton.potemkin.books.programmingInScala.chapter10

import Element.elem

abstract class Element {

  def contents: Array[String]

  def height = contents.length

  def width = if (height == 0) 0 else contents(0).length

  def above(that: Element): Element = {
    val this1 = this widen that.width
    val that1 = that widen this.width
    elem(this1.contents ++ that1.contents)
  }

  def beside(that: Element): Element = {
    val this1 = this heighten that.height
    val that1 = that heighten this.height
    elem(
      for (
        (line1, line2) <- this1.contents zip that1.contents
      ) yield line1 + line2
    )
  }

  def widen(w: Int): Element = if (w <= width) this else {
    val left = elem(' ', (w - width) / 2, height)
    val right = elem(' ', w - width - left.width, height)
    left beside this beside right
  }

  def heighten(h: Int): Element = if (h <= height) this else {
    val top = elem(' ', width, (h - height) / 2)
    val bot  = elem(' ', width, h - height - top.height)
    top above this above bot
  }

  override def toString: String = contents mkString "\n"
}

object Element {

  private class ArrayElement(val contents: Array[String]) extends Element

  private class LineElement(str: String) extends Element {
    val contents = Array(str)
    override val height = 1
    override val width = str.length
  }

  private class UniformElement(
                                ch: Char,
                                override val height: Int,
                                override val width: Int
                              ) extends Element {
    private val line = ch.toString * width

    def contents = Array.fill(height)(line)
  }

  def elem(contents: Array[String]): Element = new ArrayElement(contents)

  def elem(str: String): Element = new LineElement(str)

  def elem(ch: Char, height: Int, width: Int): Element = new UniformElement(ch, height, width)
}
