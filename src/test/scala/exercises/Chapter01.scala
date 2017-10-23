package exercises

import org.scalatest.{FreeSpec, Matchers}


class Chapter01 extends FreeSpec with Matchers {

  "The Basics" - {

    "Exercise 1" - {
      """--------
        |In the Scala REPL, type 3. followed by the Tab key. What methods can be applied?
      """.stripMargin in {

        info("The methods from the `RichInt` class")
      }
    }

    "Exercise 2" - {
      """--------
        |In the Scala REPL, compute the square root of 3, and then square that value.
        |By how much does the result differ from 3?
      """.stripMargin in {

        import math.sqrt

        val first = sqrt(3)
        val result = sqrt(first)

        result should be(sqrt(sqrt(3)))
        info(s"The result is $result and differs from 3 in ${3 - result}")
      }
    }

  }
}
