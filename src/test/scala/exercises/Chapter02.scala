package exercises

import org.scalatest.{FreeSpec, Matchers}


class Chapter02 extends FreeSpec with Matchers {

  "Control Structures and Functions" - {

    "Exercise 1" - {
      """--------
        |The signum of a number is 1 if the number is positive, –1 if it is negative, and 0 if it is zero.
        |Write a function that computes this value.
      """.stripMargin in {

        def signum(n: Int): Int = {
          if (n == 0) 0
          else if (n < 0) -1
          else 1
        }

        signum(-3) should be(-1)
        signum(0) should be(0)
        signum(14) should be(1)
      }
    }

  }
}
