package exercises

import org.scalatest.{FreeSpec, Matchers}


class Chapter16 extends FreeSpec with Matchers {

  "XML Processing" - {

    "Exercise 1" - {
      """--------
        |What is <fred/>(0)? <fred/>(0)(0)? Why?
      """.stripMargin ignore {

      }
    }

    "Exercise 2" - {
      """--------
        |What is the result of
        |
        |  <ul>
        |    <li>Opening bracket: [</li>
        |    <li>Closing bracket: ]</li>
        |    <li>Opening brace: {</li>
        |    <li>Closing brace: }</li>
        |  </ul>
        |
        |How do you fix it?
      """.stripMargin ignore {

      }
    }

    "Exercise 3" - {
      """--------
        |Contrast
        |
        |  <li>Fred</li> match { case <li>{Text(t)}</li> => t }
        |
        |and
        |
        |  <li>{"Fred"}</li> match { case <li>{Text(t)}</li> => t }
        |
        |Why do they act differently?
      """.stripMargin ignore {

      }
    }

    "Exercise 4" - {
      """--------
        |Read an XHTML file and print all img elements that don’t have an alt attribute.
      """.stripMargin ignore {

      }
    }

    "Exercise 5" - {
      """--------
        |Print the names of all images in an XHTML file. That is, print all src
        |attribute values inside img elements.
      """.stripMargin ignore {

      }
    }

    "Exercise 6" - {
      """--------
        |Read an XHTML file and print a table of all hyperlinks in the file, together
        |with their URLs. That is, print the child text and the href attribute of
        |each a element.
      """.stripMargin ignore {

      }
    }

    "Exercise 7" - {
      """--------
        |Write a function that has a parameter of type Map[String, String] and
        |returns a dl element with a dt for each key and dd for each value. For example,
        |
        |  Map("A" -> "1", "B" -> "2")\
        |
        |should yield <dl><dt>A</dt><dd>1</dd><dt>B</dt><dd>2</dd></dl>.
      """.stripMargin ignore {

      }
    }

    "Exercise 8" - {
      """--------
        |Write a function that takes a dl element and turns it into a
        |Map[String, String]. This function should be the inverse of
        |the function in the preceding exercise, provided all dt children
        |are distinct.
      """.stripMargin ignore {

      }
    }

    "Exercise 9" - {
      """--------
        |Transform an XHTML document by adding an alt="TODO" attribute
        |to all img elements without an alt attribute, preserving
        |everything else.
      """.stripMargin ignore {

      }
    }

    "Exercise 10" - {
      """---------
        |Write a function that reads an XHTML document, carries out the
        |transformation of the preceding exercise, and saves the result.
        |Be sure to preserve the DTD and any CDATA sections.
      """.stripMargin ignore {

      }
    }
  }
}
