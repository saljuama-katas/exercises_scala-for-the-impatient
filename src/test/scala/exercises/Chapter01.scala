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

    "Exercise 7" - {
      """--------
        |What do you need to import so that you can get a random prime as probablePrime(100, Random),
        |without any qualifiers before probablePrime and Random?
      """.stripMargin in {

        import BigInt.probablePrime
        import util.Random

        probablePrime(100, Random)

        info("required imports: `BigInt.probablePrime` and `(scala.)util.Random`")
      }
    }

    "Exercise 8" - {
      """--------
        |One way to create random file or directory names is to produce a random BigInt and convert
        |it to base 36, yielding a string such as "qsnvbevtomcj38o06kul". Poke around Scaladoc to
        |find a way of doing this in Scala.
      """.stripMargin in {

        import BigInt.probablePrime
        import util.Random

        val rndNumber = probablePrime(100, Random)
        val rndString = rndNumber.toString(36)

        info(s"result = $rndString")
      }
    }

    "Exercise 9" - {
      """--------
        |How do you get the first character of a string in Scala? The last character?
      """.stripMargin in {

        val string = "Hello World!"

        string.head should be('H')
        string.last should be('!')

        info("The first character is accessed with the `head` method")
        info("The last character is accessed with the `last` method")
      }
    }

  }
}
