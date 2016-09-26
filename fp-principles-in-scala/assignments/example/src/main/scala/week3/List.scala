package week3

/**
  * Created by Vivek on 7/7/16.
  */
trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def length: Int
}

class Cons[T] (val head: T, val tail: List[T]) extends List[T] {
  def isEmpty: Boolean = false
  def length: Int = tail.length + 1
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
  def length: Int = 0
}

object nth {
  def nth[T](l: List[T], n: Int): T = {
    if(n<0 || n>=l.length) throw new IndexOutOfBoundsException("list index out of range")
    else if(n == 0) l.head
    else nth(l.tail, n-1)
  }

  val list = new Cons[Int](1, new Cons(2, new Cons(3, new Nil[Int])))

  nth(list, 2)
}
