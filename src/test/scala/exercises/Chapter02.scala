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

    "Exercise 2" - {
      """--------
        |What is the value of an empty block expression {}? What is its type?
      """.stripMargin in {

        val emptyBlock = {}

        emptyBlock should be(())
        emptyBlock.getClass should be(classOf[Unit])

        info("The value of `{}` is `()`")
        info("The type of `{}` is `Unit`")
      }
    }

    "Exercise 3" - {
      """--------
        |Come up with one situation where the assignment x = y = 1 is valid in Scala.
        |(Hint: Pick a suitable type for x.)
      """.stripMargin in {

        var x = {}
        var y = 0
        x = y = 1

        y should be(1)
        x should be(())
      }
    }

    "Exercise 4" - {
      """--------
        |Write a Scala equivalent for the Java loop
        |
        |  for (int i = 10; i >= 0; i--) System.out.println(i);
      """.stripMargin in {

        val result = for (i <- (0 to 10).reverse) yield i // println(i)
        // replaced println because it is not testable

        result should be(Vector(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0))
      }
    }

    "Exercise 5" - {
      """--------
        |Write a procedure countdown(n: Int) that prints the numbers from n to 0.
      """.stripMargin in {

        def countdown(n: Int) = {
          for (i <- (0 to n).reverse) yield i // println(i)
        }

        countdown(5) should be(Vector(5, 4, 3, 2, 1, 0))
      }
    }

    "Exercise 6" - {
      """--------
        |Write a for loop for computing the product of the Unicode codes of all letters in a string.
        |For example, the product of the characters in "Hello" is 9415087488L.
      """.stripMargin in {

        (for (character <- "Hello") yield character.toLong).product should be(9415087488L)
      }
    }

    "Exercise 7" - {
      """--------
        |Solve the preceding exercise without writing a loop. (Hint: Look at the StringOps Scaladoc.)
      """.stripMargin in {

        "Hello".map(_.toLong).product should be(9415087488L)
        "Hello".foldLeft(1L)(_ * _.toLong) should be(9415087488L)
      }
    }

    "Exercise 8" - {
      """--------
        |Write a function product(s : String) that computes the product,
        |as described in the preceding exercises.
      """.stripMargin in {

        def product(s: String) = s.map(_.toLong).product

        product("Hello") should be(9415087488L)
      }
    }

    "Exercise 9" - {
      """--------
        |Make the function of the preceding exercise a recursive function.
      """.stripMargin in {

        def product(s: String): Long = {
          if (s.isEmpty) 1L
          else s.head.toLong * product(s.tail)
        }

        product("Hello") should be(9415087488L)
      }
    }

    "Exercise 10" - {
      """---------
        |Write a function that computes x ** n, where n is an integer.
        |Use the following recursive definition:
        |
        |  • x ** n = y * y if n is even and positive, where y = x ** (n / 2).
        |  • x ** n = x * x ** (n – 1) if n is odd and positive.
        |  • x ** 0 = 1.
        |  • x ** n = 1 / x ** –n if n is negative.
        |
        |Don’t use a return statement.
      """.stripMargin in {

        def power(x: Int, n: Int): Double = {
          if (n > 0) {
            if (n % 2 == 0)
              power(x, n / 2) * power(x, n / 2)
            else
              x * power(x, n - 1)
          }
          else if (n == 0)
            1
          else
            1 / power(x, -n)
        }

        power(10, 0) should be(1)
        power(10, 2) should be(100)
        power(10, -2) should be(0.01)
      }
    }

    "Exercise 11" - {
      """---------
        |Define a string interpolator date so that you can define a java.time.LocalDate
        |as date"$year-$month-$day". You need to define an “implicit” class with a date
        |method, like this:
        |
        |  implicit class DateInterpolator(val sc: StringContext) extends AnyVal {
        |    def date(args: Any*): LocalDate = ???
        |  }
        |
        |args(i) is the value of the ith expression. Convert each to a string and then to
        |an integer, and pass them to the LocalDate.of method. If you already know some
        |Scala, add error handling. Throw an exception if there aren’t three arguments, or
        |if they aren’t integers, or if they aren’t separated by dashes. (You get the
        |strings in between the expressions as sc.parts.)
      """.stripMargin in {

        import java.time.LocalDate

        implicit class DateInterpolator(val sc: StringContext) { //not value class because they can't be local
          def date(args: Any*): LocalDate = {
            val year = args(0).toString.toInt
            val month = args(1).toString.toInt
            val day = args(2).toString.toInt
            LocalDate.of(year, month, day)
          }
        }

        date"${2018}-${12}-${10}" should be(LocalDate.of(2018, 12, 10))
      }
    }
  }
}

