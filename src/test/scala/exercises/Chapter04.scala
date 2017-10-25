package exercises

import java.util

import org.scalatest.{FreeSpec, Matchers}

import scala.collection.mutable


class Chapter04 extends FreeSpec with Matchers {

  "Maps and Tuples" - {

    "Exercise 1" - {
      """--------
        |Set up a map of prices for a number of gizmos that you covet.
        |Then produce a second map with the same keys and the prices
        |at a 10 percent discount.
      """.stripMargin in {

        val gizmos = Map(
          "gadget1" -> 10.0,
          "gadget2" -> 50.0,
          "gadget3" -> 7.0,
          "gadget4" -> 15.0
        )

        val gizmosWithDiscount = gizmos.map { t => (t._1, t._2 * 0.9) }

        gizmosWithDiscount should be(Map(
          "gadget1" -> 9.0,
          "gadget2" -> 45.0,
          "gadget3" -> 6.3,
          "gadget4" -> 13.5
        ))
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
      """.stripMargin in {

        import collection.mutable

        val wordCount = mutable.HashMap[String, Int]()
        val content = io.Source.fromResource("chapter4.txt").getLines.mkString.split(" ")

        content.foreach { word =>
          val count = wordCount.getOrElse(word, 0) + 1
          wordCount(word) = count
        }

        wordCount should be(mutable.HashMap(
          "this" -> 1,
          "is" -> 3,
          "just" -> 1,
          "an" -> 2,
          "example" -> 2,
          "which" -> 1,
          "silly" -> 2,
          "but" -> 1,
          "sometimes" -> 1
        ))
      }
    }

    "Exercise 3" - {
      """--------
        |Repeat the preceding exercise with an immutable map.
      """.stripMargin in {

        var wordCount = Map[String, Int]()
        val content = io.Source.fromResource("chapter4.txt").getLines.mkString.split(" ")

        content.foreach { word =>
          val count = wordCount.getOrElse(word, 0) + 1
          wordCount += (word -> count)
        }

        wordCount should be(Map(
          "this" -> 1,
          "is" -> 3,
          "just" -> 1,
          "an" -> 2,
          "example" -> 2,
          "which" -> 1,
          "silly" -> 2,
          "but" -> 1,
          "sometimes" -> 1
        ))
      }
    }

    "Exercise 4" - {
      """--------
        |Repeat the preceding exercise with a sorted map, so that the words
        |are printed in sorted order.
      """.stripMargin in {
        info("sorting by key can be achieved using `mutable.SortedMap`")
        info("sorting by insertion order can be achieved using `mutable.LinkedHashMap`")

        import collection.mutable

        val wordCount = mutable.SortedMap[String, Int]()
        val content = io.Source.fromResource("chapter4.txt").getLines.mkString.split(" ")

        content.foreach { word =>
          val count = wordCount.getOrElse(word, 0) + 1
          wordCount(word) = count
        }

        wordCount should be(mutable.SortedMap(
          "this" -> 1,
          "is" -> 3,
          "just" -> 1,
          "an" -> 2,
          "example" -> 2,
          "which" -> 1,
          "silly" -> 2,
          "but" -> 1,
          "sometimes" -> 1
        ))
      }
    }

    "Exercise 5" - {
      """--------
        |Repeat the preceding exercise with a java.util.TreeMap that you
        |adapt to the Scala API.
      """.stripMargin in {

        import collection.JavaConverters._

        val wordCount = new util.TreeMap[String, Int]().asScala
        val content = io.Source.fromResource("chapter4.txt").getLines.mkString.split(" ")

        content.foreach { word =>
          val count = wordCount.getOrElse(word, 0) + 1
          wordCount(word) = count
        }

        wordCount should be(mutable.Map(
          "this" -> 1,
          "is" -> 3,
          "just" -> 1,
          "an" -> 2,
          "example" -> 2,
          "which" -> 1,
          "silly" -> 2,
          "but" -> 1,
          "sometimes" -> 1
        ))
      }
    }

    "Exercise 6" - {
      """--------
        |Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY,
        |and similarly for the other weekdays. Demonstrate that the elements are
        |visited in insertion order.
      """.stripMargin in {

        import java.util.Calendar._

        import collection.mutable

        val weekdays = mutable.LinkedHashMap[String, Int]()
        weekdays("Monday") = MONDAY
        weekdays("Tuesday") = TUESDAY
        weekdays("Wednesday") = WEDNESDAY
        weekdays("Thursday") = THURSDAY
        weekdays("Friday") = FRIDAY
        weekdays("Saturday") = SATURDAY
        weekdays("Sunday") = SUNDAY

        val sequenceOfDays = for ((_, value) <- weekdays) yield value
        sequenceOfDays should be(Seq(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY))
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
      """.stripMargin in {

        import collection.JavaConverters._

        val properties = System.getProperties.asScala
        val keyMaxSize = properties.keys.map(_.length).max

        def printLine(key: String, value: String, keySize: Int) = {
          val extraSpaces = " " * (keySize - key.length)
          val line = s"  $key$extraSpaces  |  $value"
          println(line)
        }

        for ((key, value) <- properties) printLine(key, value, keyMaxSize)

        // No assertions on this exercise as it can vary in different systems
      }
    }

    "Exercise 8" - {
      """--------
        |Write a function minmax(values: Array[Int]) that returns a pair containing
        |the smallest and the largest values in the array.
      """.stripMargin in {

        def minmax(values: Array[Int]) =
          (values.min, values.max)

        minmax(Array(10, 15, -2, 7, 11, 8, -7)) should be((-7, 15))
      }
    }

    "Exercise 9" - {
      """--------
        |Write a function lteqgt(values: Array[Int], v: Int) that returns a triple
        |containing the counts of values less than v, equal to v, and greater than v.
      """.stripMargin in {

        def lteggt(values: Array[Int], v: Int) =
          (values.count(_ < v), values.count(_ == v), values.count(_ > v))

        val array = Array(1, 2, 3, 4, 5, 6)
        lteggt(array, 4) should be((3, 1, 2))
        lteggt(array, 7) should be((6, 0, 0))
        lteggt(array, 0) should be((0, 0, 6))
      }
    }

    "Exercise 10" - {
      """---------
        |What happens when you zip together two strings, such as "Hello".zip("World")? Come up
        |with a plausible use case.
      """.stripMargin in {

        info("zipping 2 strings return a collection of tuples 2 characters")
        info("the size of the collection is equal to the length of the shortest string")
        info("the exceeding characters on the longest string are ignored")

        val veryFragileEncrypt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".zip("UGWIQHVCYNJOALPZEDFTRSXBKM").toMap

        "SAYHELLO".map(veryFragileEncrypt) should be("FUKCQOOP")
      }
    }
  }
}
