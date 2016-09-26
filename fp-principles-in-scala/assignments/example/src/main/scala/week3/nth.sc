import week3._

object nth {
  def nth[T](l: List[T], n: Int): T = {
    if (n < 0 || n >= l.length) throw new IndexOutOfBoundsException("list index out of range")
    else if (n == 0) l.head
    else nth(l.tail, n - 1)
  }

  val list = new Cons[Int](1, new Cons(2, new Cons(3, new Nil[Int])))

  nth(list, 2)
}