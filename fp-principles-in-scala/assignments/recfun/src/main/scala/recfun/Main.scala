package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int =
      if (c == 0 || c == r) 1
      else pascal(c-1,r-1) + pascal(c,r-1)

  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {

      def balanceWorker(index: Int, parenthCount: Int): Boolean = {

        // more right parentheses encountered than left parentheses
        if (parenthCount < 0) false

        // we have reached the end of the List
        // if parentheses are indeed balanced, parenthCount should be 0
        else if (index == chars.size) parenthCount == 0

        else if (chars(index) == '(') balanceWorker(index + 1, parenthCount + 1)

        else if (chars(index) == ')') balanceWorker(index + 1, parenthCount - 1)

        else balanceWorker(index + 1, parenthCount)
      }

      balanceWorker(0,0)
    }


  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {

      if(money == 0) 1
      else if(money < 0) 0
      else if(coins.size == 0) 0
      else
        countChange(money, coins.tail) + countChange(money-coins(0), coins)
    }
  }
