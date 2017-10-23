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

  }
}
