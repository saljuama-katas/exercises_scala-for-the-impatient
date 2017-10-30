package exercises

import org.scalatest.{FreeSpec, Matchers}


class Chapter18 extends FreeSpec with Matchers {

  "Type Parameters" - {

    "Exercise 1" - {
      """--------
        |Define an immutable class Pair[T, S] with a method swap that
        |returns a new pair with the components swapped.
      """.stripMargin ignore {

      }
    }

    "Exercise 2" - {
      """--------
        |Define a mutable class Pair[T] with a method swap that swaps the
        |components of the pair.
      """.stripMargin ignore {

      }
    }

    "Exercise 3" - {
      """--------
        |Given a class Pair[T, S], write a generic method swap that takes a pair as its
        |argument and returns a new pair with the components swapped.
      """.stripMargin ignore {

      }
    }

    "Exercise 4" - {
      """--------
        |Why don’t we need a lower bound for the replaceFirst method in Section 18.3,
        |“Bounds for Type Variables,” on page 266 if we want to replace the first component
        |of a Pair[Person] with a Student?
      """.stripMargin ignore {

      }
    }

    "Exercise 5" - {
      """--------
        |Why does RichInt implement Comparable[Int] and not Comparable[RichInt]?
      """.stripMargin ignore {

      }
    }

    "Exercise 6" - {
      """--------
        |Write a generic method middle that returns the middle element from any
        |Iterable[T]. For example, middle("World") is 'r'.
      """.stripMargin ignore {

      }
    }

    "Exercise 7" - {
      """--------
        |Look through the methods of the Iterable[+A] trait. Which methods use the
        |type parameter A? Why is it in a covariant position in these methods?
      """.stripMargin ignore {

      }
    }

    "Exercise 8" - {
      """--------
        |In Section 18.10, “Co- and Contravariant Positions,” on page 272, the
        |replaceFirst method has a type bound. Why can’t you define an equivalent
        |method on a mutable Pair[T]?
        |
        |  def replaceFirst[R >: T](newFirst: R) { first = newFirst } // Error
      """.stripMargin ignore {

      }
    }

    "Exercise 9" - {
      """--------
        |It may seem strange to restrict method parameters in an immutable class
        |Pair[+T]. However, suppose you could define
        |
        |  def replaceFirst(newFirst: T)
        |
        |in a Pair[+T]. The problem is that this method can be overridden in an
        |unsound way. Construct an example of the problem. Define a subclass
        |NastyDoublePair of Pair[Double] that overrides replaceFirst so that it
        |makes a pair with the square root of newFirst. Then construct the call
        |replaceFirst("Hello") on a Pair[Any] that is actually a NastyDoublePair.
      """.stripMargin ignore {

      }
    }

    "Exercise 10" - {
      """---------
        |Given a mutable Pair[S, T] class, use a type constraint to define a swap
        |method that can be called if the type parameters are the same.
      """.stripMargin ignore {

      }
    }
  }
}
