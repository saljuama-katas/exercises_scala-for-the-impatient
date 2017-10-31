package exercises

import org.scalatest.{FreeSpec, MustMatchers}


class Chapter17 extends FreeSpec with MustMatchers {

  "Futures" - {
    // To have an ExecutionContext
    import scala.concurrent.ExecutionContext.Implicits.global

    // To use futures
    import scala.concurrent.Future

    // To test the results, I'd say it's ok to block on tests for assertions
    import scala.concurrent.Await
    import scala.concurrent.duration._

    "Exercise 1" - {
      """--------
        |Consider the expression
        |
        |  for (n1 <- Future { Thread.sleep(1000) ; 2 }
        |       n2 <- Future { Thread.sleep(1000); 40 })
        |    println(n1 + n2)
        |
        |
        |How is the expression translated to map and flatMap calls?
        |Are the two futures executed concurrently or one after the other?
        |In which thread does the call to println occur?
      """.stripMargin in {

        val f1 = Future {
          Thread.sleep(1000)
          2
        }
        val f2 = Future {
          Thread.sleep(1000)
          40
        }
        for (n1 <- f1; n2 <- f2) n1 + n2 mustBe 42
        Await.result(f1.flatMap(n1 => f2.map(n2 => n1 + n2)), 3.seconds) mustBe 42
      }
    }

    "Exercise 2" - {
      """--------
        |Write a function doInOrder that, given two functions f: T => Future[U]
        |and g: U => Future[V], produces a function T => Future[U] that, for a
        |given t, eventually yields g(f(t)).
      """.stripMargin in {

        def doInOrder[T, U, V](f: T => Future[U], g: U => Future[V]): T => Future[V] = {
          t => f(t).flatMap(u => g(u))
        }

        val f = (s: String) => Future {
          s.toInt
        }
        val g = (i: Int) => Future {
          i.toLong
        }

        val futureResult = doInOrder(f, g)("42")
        Await.result(futureResult, 1.second) mustBe 42L
      }
    }

    "Exercise 3" - {
      """--------
        |Repeat the preceding exercise for any sequence of functions of type T => Future[T].
      """.stripMargin in {

        def doInOrder[T](functions: Seq[T => Future[T]]): T => Future[T] = { t =>
          functions match {
            case head :: Nil => head(t)
            case head :: tail => head(t).flatMap(doInOrder(tail)(_))
          }
        }

        val f1 = (value: Int) => Future {
          value + 7
        }
        val f2 = (value: Int) => Future {
          value * 2
        }
        val f3 = (value: Int) => Future {
          value - 5
        }

        val result = doInOrder(Seq(f1, f2, f3))(7)
        Await.result(result, 1.second) mustBe 23
      }
    }

    "Exercise 4" - {
      """--------
        |Write a function doTogether that, given two functions f: T => Future[U] and
        |g: U => Future[V], produces a function T => Future[(U, V)], running the two
        |computations in parallel and, for a given t, eventually yielding (f(t), g(t)).
      """.stripMargin ignore {

      }
    }

    "Exercise 5" - {
      """--------
        |Write a function that receives a sequence of futures and returns a future
        |that eventually yields a sequence of all results.
      """.stripMargin ignore {

      }
    }

    "Exercise 6" - {
      """--------
        |Write a method
        |
        |  Future[T] repeat(action: => T, until: T => Boolean)
        |
        |that asynchronously repeats the action until it produces a value that is
        |accepted by the until predicate, which should also run asynchronously. Test
        |with a function that reads a password from the console, and a function that
        |simulates a validity check by sleeping for a second and then checking that
        |the password is "secret". Hint: Use recursion.
      """.stripMargin ignore {

      }
    }

    "Exercise 7" - {
      """--------
        |Write a program that counts the prime numbers between 1 and n, as reported by
        |BigInt.isProbablePrime. Divide the interval into p parts, where p is the number
        |of available processors. Count the primes in each part in concurrent futures
        |and combine the results.
      """.stripMargin ignore {

      }
    }

    "Exercise 8" - {
      """--------
        |Write a program that asks the user for a URL, reads the web page at that URL,
        |and displays all the hyperlinks. Use a separate Future for each of these three steps.
      """.stripMargin ignore {

      }
    }

    "Exercise 9" - {
      """--------
        |Write a program that asks the user for a URL, reads the web page at that URL,
        |finds all the hyperlinks, visits each of them concurrently, and locates the
        |Server HTTP header for each of them. Finally, print a table of which servers
        |were found how often. The futures that visit each page should return the header.
      """.stripMargin ignore {

      }
    }

    "Exercise 10" - {
      """---------
        |Change the preceding exercise where the futures that visit each header update a
        |shared Java ConcurrentHashMap or Scala TrieMap. This isn’t as easy as it sounds.
        |A threadsafe data structure is safe in the sense that you cannot corrupt its
        |implementation, but you have to make sure that sequences of reads and updates are atomic.
      """.stripMargin ignore {

      }
    }

    "Exercise 11" - {
      """---------
        |Using futures, run four tasks that each sleep for ten seconds and then print
        |the current time. If you have a reasonably modern computer, it is very likely
        |that it reports four available processors to the JVM, and the futures should
        |all complete at around the same time. Now repeat with forty tasks. What happens?
        |Why? Replace the execution context with a cached thread pool. What happens now?
        |(Be careful to define the futures after replacing the implicit execution context.)
      """.stripMargin ignore {

      }
    }

    "Exercise 12" - {
      """---------
        |Write a method that, given a URL, locates all hyperlinks, makes a promise
        |for each of them, starts a task in which it will eventually fulfill all
        |promises, and returns a sequence of futures for the promises. Why would it not
        |be a good idea to return a sequence of promises?
      """.stripMargin ignore {

      }
    }

    "Exercise 13" - {
      """---------
        |Use a promise for implementing cancellation. Given a range of big integers,
        |split the range into subranges that you concurrently search for palindromic
        |primes. When such a prime is found, set it as the value of the future. All
        |tasks should periodically check whether the promise is completed, in which
        |case they should terminate.
      """.stripMargin ignore {

      }
    }
  }
}