package exercises

import org.scalatest.{FreeSpec, Matchers}


class Chapter07 extends FreeSpec with Matchers {

  "Packages and Imports" - {

    "Exercise 1" - {
      """--------
        |Write an example program to demonstrate that
        |
        |  package com.horstmann.impatient
        |
        |is not the same as
        |
        |  package com
        |  package horstmann
        |  package impatient
      """.stripMargin in {

        // skipping this test as it is not possible to resolve this exercise inside a test

        info("with package com.horstman.impatient the classes from packages com and com.horstman are not visible")
        info("with the triple declaration, all classes from com, com.horstman, and com.horstman.impatient are visible")
      }
    }

    "Exercise 2" - {
      """--------
        |Write a puzzler that baffles your Scala friends, using a package com that
        |isn’t at the top level.
      """.stripMargin ignore {

      }
    }

    "Exercise 3" - {
      """--------
        |Write a package random with functions nextInt(): Int, nextDouble(): Double,
        |and setSeed(seed: Int): Unit. To generate random numbers, use the linear
        |congruential generator
        |
        |  next = (previous × a + b) mod 2 ** n
        |
        |where a = 1664525, b = 1013904223, n = 32, and the initial value of previous
        |is seed.
      """.stripMargin in {

        import random._ // see implementation below

        setSeed(10)
        val firstInt = nextInt()
        val secondInt = nextInt()
        val firstDouble = nextDouble()
        val secondDouble = nextDouble()

        // this is to couple the test to the implementation, don't do this!
        val calculatedNextInt = (seed: Int) => (seed * 1664525 + 1013904223) % math.pow(2.0, 32).toInt
        val calculatedNextDouble = (seed: Double) => (seed * 1664525 + 1013904223) % math.pow(2.0, 32)

        firstInt should be(calculatedNextInt(10))
        secondInt should be(calculatedNextInt(firstInt))
        firstDouble should be(calculatedNextDouble(10.0))
        secondDouble should be(calculatedNextDouble(firstDouble))
      }
    }

    "Exercise 4" - {
      """--------
        |Why do you think the Scala language designers provided the package object
        |syntax instead of simply letting you add functions and variables to a package?
      """.stripMargin in {

        info("packages are open")
        info("package objects are closed")
        info("there can be only one package object per package")
        info("package objects enforce to have all the members of the package in the same file")
      }
    }

    "Exercise 5" - {
      """--------
        |What is the meaning of private[com] def giveRaise(rate: Double)? Is it useful?
      """.stripMargin in {

        info("giveRaise method is now accessible up to package `com` in the nested hierarchy")
      }
    }

    "Exercise 6" - {
      """--------
        |Write a program that copies all elements from a Java hash map into a
        |Scala hash map. Use imports to rename both classes.
      """.stripMargin in {


        import java.util.{HashMap => JavaMap}

        import collection.mutable.{HashMap => ScalaMap}

        object MapConverter {
          def copyMap[K <: Any, V <: Any](input: JavaMap[K, V]): ScalaMap[K, V] = {
            val result = new ScalaMap[K, V]()
            input forEach { (k, v) => result += k -> v }
            result
          }
        }

        val javaMap = new JavaMap[Int, String]()
        javaMap.put(1, "Hello")
        javaMap.put(2, "World")
        val scalaMap: ScalaMap[Int, String] = MapConverter.copyMap(javaMap)

        scalaMap.size shouldBe 2
        scalaMap(1) shouldBe "Hello"
        scalaMap.get(2) shouldBe Some("World")
      }
    }

    "Exercise 7" - {
      """--------
        |In the preceding exercise, move all imports into the innermost scope possible.
      """.stripMargin in {

        object MapConverter {

          import java.util.{HashMap => JavaMap}

          import collection.mutable.{HashMap => ScalaMap}

          def copyMap[K <: Any, V <: Any](input: JavaMap[K, V]): ScalaMap[K, V] = {
            val result = new ScalaMap[K, V]()
            input forEach { (k, v) => result += k -> v }
            result
          }
        }

        val javaMap = new java.util.HashMap[Int, String]()
        javaMap.put(1, "Hello")
        javaMap.put(2, "World")

        val scalaMap: collection.mutable.HashMap[Int, String] = MapConverter.copyMap(javaMap)

        scalaMap.size shouldBe 2
        scalaMap(1) shouldBe "Hello"
        scalaMap.get(2) shouldBe Some("World")
      }
    }

    "Exercise 8" - {
      """--------
        |What is the effect of
        |
        |  import java._
        |  import javax._
        |
        |Is this a good idea?
      """.stripMargin in {

        val scalaRnd = new util.Random()

        import java._
        val javaRnd = new util.Random()

        info("the imports override the members from scala subpackages members that have the same name `full name`")
      }
    }

    "Exercise 9" - {
      """--------
        |Write a program that imports the java.lang.System class, reads the user name
        |from the user.name system property, reads a password from the StdIn object,
        |and prints a message to the standard error stream if the password is not
        |"secret". Otherwise, print a greeting to the standard output stream. Do not
        |use any other imports, and do not use any qualified names (with dots).
      """.stripMargin in {

        def login(): String = {
          import java.lang.System._

          import io.StdIn._
          val username: String = getProperty("user.name")
          val password: String = readLine()
          if (password == "secret") s"Hello $username"
          else "Invalid password"
        }

        import java.io.ByteArrayInputStream

        System.setProperty("user.name", "John")
        val secretIn = new ByteArrayInputStream("secret".getBytes)
        Console.withIn(secretIn) {
          login() should be("Hello John")
        }

        val invalidIn = new ByteArrayInputStream("somethingElse".getBytes)
        Console.withIn(invalidIn) {
          login() should be("Invalid password")
        }
      }
    }

    "Exercise 10" - {
      """---------
        |Apart from StringBuilder, what other members of java.lang does the scala package override?
      """.stripMargin in {

        info("Boolean")
        info("Double")
        info("Exception")
        info("Float")
        info("Iterable")
        info("Long")
        info("Short")
        info("String")
      }
    }
  }
}

package object random {

  private val a = 1664525
  private val b = 1013904223
  private val n = 32
  private var seed: Int = 1
  private var lastInt: Int = seed
  private var lastDouble: Double = seed

  def setSeed(seed: Int) {
    this.seed = seed
    this.lastInt = seed
    this.lastDouble = seed
  }

  def nextInt(): Int = {
    val newInt: Int = (lastInt * a + b) % math.pow(2.0, n).toInt
    lastInt = newInt
    newInt
  }

  def nextDouble(): Double = {
    val newDouble: Double = (lastDouble * a + b) % math.pow(2.0, n)
    lastDouble = newDouble
    newDouble
  }
}