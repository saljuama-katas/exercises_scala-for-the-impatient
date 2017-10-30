package exercises

import org.scalatest.{FreeSpec, Matchers}


class Chapter09 extends FreeSpec with Matchers {

  "Files and Regular Expressions" - {

    "Exercise 1" - {
      """--------
        |Write a Scala code snippet that reverses the lines in a file (making the last
        |line the first one, and so on).
      """.stripMargin ignore {

      }
    }

    "Exercise 2" - {
      """--------
        |Write a Scala program that reads a file with tabs, replaces each tab with
        |spaces so that tab stops are at n-column boundaries, and writes the result
        |to the same file.
      """.stripMargin ignore {

      }
    }

    "Exercise 3" - {
      """--------
        |Write a Scala code snippet that reads a file and prints all words with
        |more than 12 characters to the console. Extra credit if you can do this
        |in a single line.
      """.stripMargin ignore {

      }
    }

    "Exercise 4" - {
      """--------
        |Write a Scala program that reads a text file containing only floating-point
        |numbers. Print the sum, average, maximum, and minimum of the numbers in the file.
      """.stripMargin ignore {

      }
    }

    "Exercise 5" - {
      """--------
        |Write a Scala program that writes the powers of 2 and their reciprocals
        |to a file, with the exponent ranging from 0 to 20. Line up the columns:
        |
        |    1    1
        |    2    0.5
        |    4    0.25
        |  ...    ...
      """.stripMargin ignore {

      }
    }

    "Exercise 6" - {
      """--------
        |Make a regular expression searching for quoted strings "like this, maybe with \" or \\"
        |in a Java or C++ program. Write a Scala program that prints out all such strings in a
        |source file.
      """.stripMargin ignore {

      }
    }

    "Exercise 7" - {
      """--------
        |Write a Scala program that reads a text file and prints all tokens in the
        |file that are not floating-point numbers. Use a regular expression.
      """.stripMargin ignore {

      }
    }

    "Exercise 8" - {
      """--------
        |Write a Scala program that prints the src attributes of all img tags of a
        |web page. Use regular expressions and groups.
      """.stripMargin ignore {

      }
    }

    "Exercise 9" - {
      """--------
        |Write a Scala program that counts how many files with .class extension are in a
        |given directory and its subdirectories.
      """.stripMargin ignore {

      }
    }

    "Exercise 10" - {
      """---------
        |Expand the example in Section 9.8, “Serialization,” on page 113. Construct a
        |few Person objects, make some of them friends of others, and save an Array[Person]
        |to a file. Read the array back in and verify that the friend relations are intact.
      """.stripMargin ignore {

      }
    }
  }
}
