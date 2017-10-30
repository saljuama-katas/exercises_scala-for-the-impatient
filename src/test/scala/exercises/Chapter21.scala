package exercises

import org.scalatest.{FreeSpec, Matchers}


class Chapter21 extends FreeSpec with Matchers {

  "Implicits" - {

    "Exercise 1" - {
      """--------
        |How does -> work? That is, how can "Hello" -> 42 and 42 -> "Hello"
        |be pairs ("Hello", 42) and (42, "Hello")? Hint: Predef.ArrowAssoc.
      """.stripMargin ignore {

      }
    }

    "Exercise 2" - {
      """--------
        |Define an operator +% that adds a given percentage to a value. For
        |example, 120 +% 10 should be 132. Use an implicit class.
      """.stripMargin ignore {

      }
    }

    "Exercise 3" - {
      """--------
        |Define a ! operator that computes the factorial of an integer. For
        |example, 5.! is 120. Use an implicit class.
      """.stripMargin ignore {

      }
    }

    "Exercise 4" - {
      """--------
        |Some people are fond of “fluent APIs” that read vaguely like English
        |sentences. Create such an API for reading integers, floating-point
        |numbers, and strings from the console. For example: Read in
        |aString askingFor "Your name" and anInt askingFor "Your age" and
        |aDouble askingFor "Your weight".
      """.stripMargin ignore {

      }
    }

    "Exercise 5" - {
      """--------
        |Provide the machinery that is needed to compute
        |
        |  smaller(Fraction(1, 7), Fraction(2, 9))
        |
        |with the Fraction class of Chapter 11. Supply an implicit class RichFraction that
        |extends Ordered[Fraction].
      """.stripMargin ignore {

      }
    }

    "Exercise 6" - {
      """--------
        |Compare objects of the class java.awt.Point by lexicographic comparison.
      """.stripMargin ignore {

      }
    }

    "Exercise 7" - {
      """--------
        |Continue the previous exercise, comparing two points according to their
        |distance to the origin. How can you switch between the two orderings?
      """.stripMargin ignore {

      }
    }

    "Exercise 8" - {
      """--------
        |Use the implicitly command in the REPL to summon the implicit objects
        |described in Section 21.5, “Implicit Parameters,” on page 328 and
        |Section 21.6, “Implicit Conversions with Implicit Parameters,” on
        |page 329. What objects do you get?
      """.stripMargin ignore {

      }
    }

    "Exercise 9" - {
      """--------
        |Explain why Ordering is a type class and why Ordered is not.
      """.stripMargin ignore {

      }
    }

    "Exercise 10" - {
      """---------
        |Generalize the average method in Section 21.8, “Type Classes,”
        |on page 331 to a Seq[T].
      """.stripMargin ignore {

      }
    }

    "Exercise 11" - {
      """---------
        |Make String a member of the NumberLike type class in Section 21.8,
        |“Type Classes,” on page 331. The divBy method should retain every
        |nth letter, so that average("Hello", "World") becomes "Hlool".
      """.stripMargin ignore {

      }
    }

    "Exercise 12" - {
      """---------
        |Look up the =:= object in Predef.scala. Explain how it works.
      """.stripMargin ignore {

      }
    }

    "Exercise 13" - {
      """---------
        |The result of "abc".map(_.toUpper) is a String, but the result
        |of "abc".map(_.toInt) is a Vector. Find out why.
      """.stripMargin ignore {

      }
    }
  }
}
