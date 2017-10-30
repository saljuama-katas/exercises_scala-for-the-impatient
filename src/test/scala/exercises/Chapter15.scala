package exercises

import org.scalatest.{FreeSpec, Matchers}


class Chapter15 extends FreeSpec with Matchers {

  "Annotations" - {

    "Exercise 1" - {
      """--------
        |Write four JUnit test cases that use the @Test annotation with and without
        |each of its arguments. Run the tests with JUnit.
      """.stripMargin ignore {

      }
    }

    "Exercise 2" - {
      """--------
        |Make an example class that shows every possible position of an annotation.
        |Use @deprecated as your sample annotation.
      """.stripMargin ignore {

      }
    }

    "Exercise 3" - {
      """--------
        |Which annotations from the Scala library use one of the meta-annotations
        |@param, @field, @getter, @setter, @beanGetter, or @beanSetter?
      """.stripMargin ignore {

      }
    }

    "Exercise 4" - {
      """--------
        |Write a Scala method sum with variable integer arguments that returns the
        |sum of its arguments. Call it from Java.
      """.stripMargin ignore {

      }
    }

    "Exercise 5" - {
      """--------
        |Write a Scala method that returns a string containing all lines of a file.
        |Call it from Java.
      """.stripMargin ignore {

      }
    }

    "Exercise 6" - {
      """--------
        |Write a Scala object with a volatile Boolean field. Have one thread sleep
        |for some time, then set the field to true, print a message, and exit. Another
        |thread will keep checking whether the field is true. If so, it prints a
        |message and exits. If not, it sleeps for a short time and tries again. What
        |happens if the variable is not volatile?
      """.stripMargin ignore {

      }
    }

    "Exercise 7" - {
      """--------
        |Give an example to show that the tail recursion optimization is not valid when
        |a method can be overridden.
      """.stripMargin ignore {

      }
    }

    "Exercise 8" - {
      """--------
        |Add the allDifferent method to an object, compile and look at the bytecode.
        |What methods did the @specialized annotation generate?
      """.stripMargin ignore {

      }
    }

    "Exercise 9" - {
      """--------
        |The Range.foreach method is annotated as @specialized(Unit). Why? Look at the
        |bytecode by running
        |
        |  javap -classpath /path/to/scala/lib/scala-library.jar
        |    scala.collection.immutable.Range
        |
        |and consider the @specialized annotations on Function1. Click on the Function1.scala
        |link in Scaladoc to see them.
      """.stripMargin ignore {

      }
    }

    "Exercise 10" - {
      """---------
        |Addassert(n>=0)toafactorialmethod.Compilewithassertionsenabledand verify that
        |factorial(-1) throws an exception. Compile without assertions. What happens?
        |Use javap to check what happened to the assertion call.
      """.stripMargin ignore {

      }
    }
  }
}
