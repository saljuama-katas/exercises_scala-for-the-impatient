package exercises

import org.scalatest.{FreeSpec, Matchers}


class Chapter04 extends FreeSpec with Matchers {

  "Maps and Tuples" - {

    "Exercise 1" - {
      """--------
        |Set up a map of prices for a number of gizmos that you covet.
        |Then produce a second map with the same keys and the prices
        |at a 10 percent discount.
      """.stripMargin ignore {

      }
    }

    "Exercise 2" - {
      """--------
        |Write a program that reads words from a file. Use a mutable
        |map to count how often each word appears. To read the words,
        |simply use a java.util.Scanner:
        |
        |  val in = new java.util.Scanner(new java.io.File("myfile.txt"))
        |  while (in.hasNext()) process in.next()
        |
        |Or look at Chapter 9 for a Scalaesque way.
        |At the end, print out all words and their counts.
      """.stripMargin ignore {

      }
    }

    "Exercise 3" - {
      """--------
        |Repeat the preceding exercise with an immutable map.
      """.stripMargin ignore {

      }
    }

    "Exercise 4" - {
      """--------
        |Repeat the preceding exercise with a sorted map, so that the words
        |are printed in sorted order.
      """.stripMargin ignore {

      }
    }

    "Exercise 5" - {
      """--------
        |Repeat the preceding exercise with a java.util.TreeMap that you
        |adapt to the Scala API.
      """.stripMargin ignore {

      }
    }

    "Exercise 6" - {
      """--------
        |Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY,
        |and similarly for the other weekdays. Demonstrate that the elements are
        |visited in insertion order.
      """.stripMargin ignore {

      }
    }

    "Exercise 7" - {
      """--------
        |Print a table of all Java properties reported by the getProperties method
        |of the java.lang.System class, like this:
        |
        |  java.runtime.name       |  Java(TM) SE Runtime Environment
        |  sun.boot.library.path   |  /home/apps/jdk1.6.0_21/jre/lib/i386
        |  java.vm.version         |  17.0-b16
        |  java.vm.vendor          |  Sun Microsystems Inc.
        |  java.vendor.url         |  http://java.sun.com/
        |  path.separator          |  :
        |  java.vm.name            |  Java HotSpot(TM) Server VM
        |
        |You need to find the length of the longest key before you can print the table.
      """.stripMargin ignore {

      }
    }

    "Exercise 8" - {
      """--------
        |Write a function minmax(values: Array[Int]) that returns a pair containing
        |the smallest and the largest values in the array.
      """.stripMargin ignore {

      }
    }

    "Exercise 9" - {
      """--------
        |Write a function lteqgt(values: Array[Int], v: Int) that returns a triple
        |containing the counts of values less than v, equal to v, and greater than v.
      """.stripMargin ignore {

      }
    }

    "Exercise 10" - {
      """---------
        |Whathappenswhenyouziptogethertwostrings,suchas"Hello".zip("World")? Come up
        |with a plausible use case.
      """.stripMargin ignore {

      }
    }
  }
}
