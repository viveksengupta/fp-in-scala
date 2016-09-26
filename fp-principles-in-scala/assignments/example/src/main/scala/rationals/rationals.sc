object rationals {

  class Rational(x: Int, y: Int) {
    require(y > 0, "denominator must be greater than zero")

    def this(x: Int) = this(x, 1)

    private def gcd(a: Int, b: Int): Int =
      if(b==0) a
      else gcd(b, a%b)

    private val g = gcd(x, y)

    def numer = if(y/g < 0) -x/g else x/g
    def denom = if(y/g < 0) -y/g else y/g

    def add(other: Rational) =
      new Rational(
        numer * other.denom + denom * other.numer,
        denom * other.denom
      )

    def sub(other: Rational) =
      add(other.neg)

    override def toString = numer + "/" + denom

    def neg: Rational =
      new Rational(-numer, denom)

    def less(other: Rational) =
      numer * other.denom < denom * other.numer

    def max(other: Rational) =
      if(less(other)) other
      else this
  }

  val x = new Rational(1, 3)
  x.numer
  x.denom

  val y = new Rational(5, 7)

  x.add(y)
  y.neg
  x.sub(y)
  x.neg

  val z = new Rational(3, 2)

  x.sub(y).sub(z)
  x.less(y)
  x.max(y)

  //val l = new Rational(1, 0)
  val m = new Rational(2)

  type Set = Int => Boolean
  def contains(s: Set, elem: Int): Boolean = s(elem)
}

