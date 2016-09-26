package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
    assert(!contains(x => x >= -1000 && x <= 1000, 2000))
    assert(contains(x => x >= -1000 && x <= 1000, 200))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(x) contains x and x only") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "singletonSet(1) should contain 1")
      assert(!contains(s2, 1), "singletonSet(2) should not contain 1")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "union({1}, {2}) should contain 1")
      assert(contains(s, 2), "union({1}, {2}) should contain 2")
      assert(!contains(s, 3), "union({1}, {2}) should not contain 3")
    }
  }


  test("intersect contains common elements of each set") {
    new TestSets {
      val s = intersect(s1, s2)
      assert(!contains(s, 1), "intersect({1}, {2}) should not contain 1")
      assert(!contains(s, 2), "intersect({1}, {2}) should not contain 2")

      val t = intersect(union(s1,s3),union(s2,s3))
      assert(contains(t, 3), "intersect( union({1}, {3}), union({2}, {3}) ) should contain 3")
    }
  }

  test("diff contains the elements present in the 1st set but not in the 2nd set") {
    new TestSets {
      val s = diff(s1, s2)
      assert(contains(s, 1), "diff({1}, {2}) should contain 1")
      assert(!contains(s, 2), "diff({1}, {2}) should not contain 2")

      val t = diff(union(s2, s3), s2)
      assert(contains(t, 3), "diff( union({2}, {3}), {2} ) should contain 3")
      assert(!contains(t, 2), "diff( union({2}, {3}), {2} ) should not contain 2")

    }
  }

  test("filter contains the elements of the set which satisfy the predicate") {
    new TestSets {
      val s = filter(union(s1, s2), x => x * x == x)
      assert(contains(s, 1), "filter( union({1}, {2}), x => x*x == x) should contain 1")
      assert(!contains(s, 2), "filter( union({1}, {2}), x => x*x == x) should not contain 2")
    }
  }

  test("forall") {
    new TestSets {
      val s = union(s1, s2)
      assert(forall(s, x => x <= 1000 && x >= -1000))

      val t = union(s3, singletonSet(2000))
      assert(forall(t, x => x <= 1000 && x >= -1000))
    }
  }
}
