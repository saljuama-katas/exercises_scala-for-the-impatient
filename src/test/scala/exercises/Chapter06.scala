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
      """.stripMargin in {

        abstract class UnitConversion {
          def apply(value: Double): Double
        }

        object InchesToCentimeters extends UnitConversion {
          override def apply(value: Double): Double = value * 2.54
        }

        object GallonsToLiters extends UnitConversion {
          override def apply(value: Double): Double = value / 0.26417
        }

        object MilesToKilometers extends UnitConversion {
          override def apply(value: Double): Double = value * 1.609344
        }

        InchesToCentimeters(1.0) should be(2.54)
        GallonsToLiters(1.0) should be(3.7854411931710636)
        MilesToKilometers(1.0) should be(1.609344)
      }
    }

    "Exercise 3" - {
      """--------
        |Define an Origin object that extends java.awt.Point. Why is this not actually
        |a good idea? (Have a close look at the methods of the Point class.)
      """.stripMargin in {

        object Origin extends java.awt.Point

        val origin = Origin

        origin.x should be(0)
        origin.y should be(0)

        origin.x = 2
        origin.y = 7
        origin.x should be(2)
        origin.y should be(7)

        info("java.awt.Point offers mutability")
        info("Origin represents a point that should not be moved")
      }
    }

    "Exercise 4" - {
      """--------
        |Define a Point class with a companion object so that you can construct Point
        |instances as Point(3, 4), without using new.
      """.stripMargin in {

        class Point(val x: Int, val y: Int)

        object Point {
          def apply(x: Int, y: Int): Point = new Point(x, y)
        }

        val p1 = Point(3, 4)

        p1.x should be(3)
        p1.y should be(4)
      }
    }

    "Exercise 5" - {
      """--------
        |Write a Scala application, using the App trait, that prints its command-line
        |arguments in reverse order, separated by spaces. For example, scala Reverse
        |Hello World should print World Hello.
      """.stripMargin in {

        object Application extends App {
          for (argument <- args.reverse) print(s"$argument ")
        }
      }
    }

    "Exercise 6" - {
      """--------
        |Write an enumeration describing the four playing card suits so that the
        |toString method returns ♥, ♠, ♦ or ♣
      """.stripMargin in {

        object CardSuite extends Enumeration {
          type CardSuite = Value
          val Clubs: CardSuite = Value("♣")
          val Diamonds: CardSuite = Value("♦")
          val Hearts: CardSuite = Value("♥")
          val Spades: CardSuite = Value("♠")
        }

        import CardSuite._

        Clubs.toString should be("♣")
        Diamonds.toString should be("♦")
        Hearts.toString should be("♥")
        Spades.toString should be("♠")
      }
    }

    "Exercise 7" - {
      """--------
        |Implement a function that checks whether a card suit value from the
        |preceding exercise is red.
      """.stripMargin in {

        object CardSuite extends Enumeration {
          type CardSuite = Value
          val Clubs: CardSuite = Value("♣")
          val Diamonds: CardSuite = Value("♦")
          val Hearts: CardSuite = Value("♥")
          val Spades: CardSuite = Value("♠")
        }

        import CardSuite._

        def isRed(cardSuite: CardSuite): Boolean = cardSuite == Diamonds || cardSuite == Hearts

        isRed(Diamonds) should be(true)
        isRed(Hearts) should be(true)
        isRed(Clubs) should be(false)
        isRed(Spades) should be(false)
      }
    }

    "Exercise 8" - {
      """--------
        |Write an enumeration describing the eight corners of the RGB color
        |cube. As IDs, use the color values (for example, 0xff0000 for Red).
      """.stripMargin in {

        object RgbCorners extends Enumeration {
          type RgbCorners = Value
          val Red: RgbCorners = Value(0xff0000)
          val Green: RgbCorners = Value(0x00ff00)
          val Blue: RgbCorners = Value(0x0000ff)
          val Yellow: RgbCorners = Value(0xffff00)
          val Magenta: RgbCorners = Value(0xff00ff)
          val Cyan: RgbCorners = Value(0x00ffff)
          val Black: RgbCorners = Value(0x000000)
          val White: RgbCorners = Value(0xffffff)
        }

        import RgbCorners._

        Red.id + Blue.id should be(Magenta.id)
      }
    }
  }
}
