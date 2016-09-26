object exercise {
  def product(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 1
    else f(a) * product(f)(a + 1, b)

  product(x => x * x)(3, 7)

  def factorial(n: Int) =
    product(x => x)(1, n)

  factorial(5)

  def sum(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sum(f)(a + 1, b)

  sum(x => x)(4, 5)

  def sumProd(f: Int => Int, combine: (Int, Int) => Int, unitVal: Int)(a: Int, b: Int): Int =
    if (a > b) unitVal
    else combine(f(a), sumProd(f, combine, unitVal)(a + 1, b))

  def productNew(f: Int => Int)(a: Int, b: Int) =
    sumProd(f, (x,y) => x*y, 1)(a, b)

  productNew(x => x)(4, 5)

  def sumNew(f: Int => Int)(a: Int, b:Int) =
    sumProd(f, (x,y) => x+y, 0)(a, b)

  sumNew(x => x)(4, 5)
}