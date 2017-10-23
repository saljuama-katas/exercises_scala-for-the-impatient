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

    "Exercise 3" - {
      """--------
        |Repeat the preceding assignment, but produce a new array with the swapped values.
        |Use for/yield.
      """.stripMargin in {

        val array = Array(1, 2, 3, 4, 5)

        val result = (for (i <- array.indices) yield
          if (i % 2 == 0)
            if (i < array.length - 1)
              array(i + 1)
            else
              array(i)
          else
            array(i - 1)
          ).toArray

        result should be(Array(2, 1, 4, 3, 5))
      }
    }

    "Exercise 4" - {
      """--------
        |Given an array of integers, produce a new array that contains all positive
        |values of the original array, in their original order, followed by all values
        |that are zero or negative, in their original order.
      """.stripMargin in {

        val array = Array(1, -2, 3, 0, -5, 7, 4, -2, 0, 5)

        val positives = for (x <- array if x > 0) yield x
        val nonPositives = for (x <- array if x <= 0) yield x
        val result = positives ++ nonPositives

        result should be(Array(1, 3, 7, 4, 5, -2, 0, -5, -2, 0))
      }
    }

    "Exercise 5" - {
      """--------
        |How do you compute the average of an Array[Double]?
      """.stripMargin in {

        val array = Array(1.0, 2.0)
        val average = array.sum / array.length

        average should be(1.5)
      }
    }

    "Exercise 6" - {
      """--------
        |How do you rearrange the elements of an Array[Int] so that they appear in
        |reverse sorted order? How do you do the same with an ArrayBuffer[Int]?
      """.stripMargin in {

        import collection.mutable.ArrayBuffer

        val array = Array(3, 1, 6, 2, -1, 4)
        val arrayBuffer = ArrayBuffer(3, 1, 6, 2, -1, 4)

        array.sorted.reverse should be(Array(6, 4, 3, 2, 1, -1))
        arrayBuffer.sorted.reverse should be(ArrayBuffer(6, 4, 3, 2, 1, -1))
      }
    }

  }
}
