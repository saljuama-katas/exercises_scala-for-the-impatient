package exercises

import org.scalatest.{FreeSpec, Matchers}


class Chapter03 extends FreeSpec with Matchers {

  "Working with Arrays" - {

    "Exercise 1" - {
      """--------
        |Write a code snippet that sets a to an array of n random integers between 0 (inclusive) and n (exclusive).
      """.stripMargin in {

        import util.Random

        def randomArray(n: Int) = (for (_ <- 0 until n) yield Random.nextInt(n)).toArray

        val a = randomArray(5)

        a should have length 5
        for (value <- a) {
          value should be < 5
          value should be >= 0
        }
      }
    }

    "Exercise 2" - {
      """--------
        |Write a loop that swaps adjacent elements of an array of integers. For example,
        |Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5).
      """.stripMargin in {

        val array = Array(1, 2, 3, 4, 5)

        for (i <- array.indices by 2) {
          if (i + 1 < array.length) {
            val aux = array(i)
            array(i) = array(i + 1)
            array(i + 1) = aux
          }
        }

        array should be(Array(2, 1, 4, 3, 5))
      }
    }
  }
}
