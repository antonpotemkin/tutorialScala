import scala.collection.mutable.ArrayBuffer

abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}
class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]

  override def get(): Int = buf.remove(0)

  override def put(x: Int): Unit = buf += x
}

val queue = new BasicIntQueue()
queue.put(3)
queue.put(2)
assert(queue.get() == 3)

trait Doubling extends IntQueue {
  abstract override def put(x: Int): Unit = super.put(x * 2)
}

val dQueue = new BasicIntQueue with Doubling
dQueue.put(3)
dQueue.put(-10)
assert(dQueue.get() == 6)
assert(dQueue.get() == -20)

trait Incrementing extends IntQueue {
  abstract override def put(x: Int): Unit = super.put(x + 1)
}

val dIQueue = new BasicIntQueue with Incrementing with Doubling
dIQueue.put(5)
assert(dIQueue.get() == 11)

val iDQueue = new BasicIntQueue with Doubling with Incrementing
iDQueue.put(5)
assert(iDQueue.get() == 12)


