package exercises

import org.scalatest.{FreeSpec, Matchers}


class Chapter06 extends FreeSpec with Matchers {

  "Objects" - {

    "Exercise 1" - {
      """--------
        |Write an object Conversions with methods inchesToCentimeters, gallonsToLiters
        |and milesToKilometers.
      """.stripMargin in {

        object Conversions {
          def inchesToCentimeters(inches: Double): Double = inches * 2.54
          def gallonsToLiters(gallons: Double): Double = gallons / 0.26417 // assuming US gallons liquid
          def milesToKilometers(miles: Double): Double = miles * 1.609344
        }

        Conversions.inchesToCentimeters(1.0) should be(2.54)
        Conversions.gallonsToLiters(1.0) should be(3.7854411931710636)
        Conversions.milesToKilometers(1.0) should be(1.609344)
      }
    }

    "Exercise 2" - {
      """--------
        |The preceding problem wasn't very object-oriented. Provide a general superclass
        |UnitConversion and define objects InchesToCentimeters, GallonsToLiters, and
        |MilesToKilometers that extend it.
      """.stripMargin ignore {

      }
    }

    "Exercise 3" - {
      """--------
        |Define an Origin object that extends java.awt.Point. Why is this not actually
        |a good idea? (Have a close look at the methods of the Point class.)
      """.stripMargin ignore {

      }
    }

    "Exercise 4" - {
      """--------
        |Define a Point class with a companion object so that you can construct Point
        |instances as Point(3, 4), without using new.
      """.stripMargin ignore {

      }
    }

    "Exercise 5" - {
      """--------
        |Write a Scala application, using the App trait, that prints its command-line
        |arguments in reverse order, separated by spaces. For example, scala Reverse
        |Hello World should print World Hello.
      """.stripMargin ignore {

      }
    }

    "Exercise 6" - {
      """--------
        |Write an enumeration describing the four playing card suits so that the
        |toString method returns ♥, ♠, ♦ or ♣
      """.stripMargin ignore {


      }
    }

    "Exercise 7" - {
      """--------
        |Implement a function that checks whether a card suit value from the
        |preceding exercise is red.
      """.stripMargin ignore {

      }
    }

    "Exercise 8" - {
      """--------
        |Write an enumeration describing the eight corners of the RGB color
        |cube. As IDs, use the color values (for example, 0xff0000 for Red).
      """.stripMargin ignore {

      }
    }
  }
}
