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


    "Exercise 3" - {
      """--------
        |Are the res variables val or var?
      """.stripMargin in {

        info("The `res` variables in the REPL are `val`")
      }
    }

    "Exercise 4" - {
      """--------
        |Scala lets you multiply a string with a number, try out "crazy" * 3 in the REPL.
        |What does this operation do? Where can you find it in Scaladoc?
      """.stripMargin in {

        val result = "crazy" * 3
        result should be("crazycrazycrazy")

        info(s"result = $result")
        info("`StringOps` class has a method `*`")
      }
    }

    "Exercise 5" - {
      """--------
        |What does 10 max 2 mean? In which class is the max method defined?
      """.stripMargin in {

        val result = 10 max 2
        result should be(10)

        info(s"This is the equivalent of executing 10.max(2) and returns $result")
        info("`max` is defined in the `RichInt` class")
      }
    }

    "Exercise 6" - {
      """--------
        |Using BigInt, compute 2 ** 1024.
      """.stripMargin in {

        val base: BigInt = 2
        val result = base.pow(1024)

        info(s"result = $result")
      }
    }

  }
}
